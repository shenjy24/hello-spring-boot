package com.jonas.module.weblog.util;

import com.google.gson.JsonObject;
import com.jonas.util.GsonUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shenjy
 * @createTime 2022/7/22 16:02
 * @description WebLogParser
 */
public class WebLogParser {

    public static List<String> parse(String logArray) {
        List<String> logs = new LinkedList<>();
        String[] logItems = logArray.split(",");
        for (String item : logItems) {
            String content = URLDecoder.decode(item);
            JsonObject jsonObject = GsonUtils.toJsonObject(content);
            System.out.println(jsonObject);
            int version = jsonObject.has(WebLogFieldEnum.VERSION.key) ?
                    jsonObject.get(WebLogFieldEnum.VERSION.key).getAsInt() : 0;
            if (version == 0) {
                parseByVersion0(jsonObject, logs);
            } else if (version == 1) {
                parseByVersion1(jsonObject, logs);
            }
        }
        return logs;
    }

    private static void parseByVersion0(JsonObject jsonObject, List<String> logs) {
        String log = jsonObject.get(WebLogFieldEnum.LOG.key).getAsString();
        if (StringUtils.isNotEmpty(log)) {
            String decoded = new String(Base64.decodeBase64(log));
            logs.add(URLDecoder.decode(decoded));
        }
    }

    private static void parseByVersion1(JsonObject jsonObject, List<String> logs) {
        byte[] log = Base64.decodeBase64(jsonObject.get(WebLogFieldEnum.LOG.key).getAsString());
        String iv = jsonObject.get(WebLogFieldEnum.IV.key).getAsString();
        String secretKey = jsonObject.get(WebLogFieldEnum.KEY.key).getAsString();
        if (isAllNotEmpty(iv, secretKey) && log != null) {
            String content = decryptContent(log, iv, secretKey);
            if (content != null) {
                logs.add(content);
            }
        }
    }

    private static String decryptContent(byte[] log, String iv, String secretKey) {
        return WebLogDecryptHelper.create(secretKey).doDecrypt(iv, log);
    }

    public static boolean isAllNotEmpty(String... strings) {
        for (String item : strings) {
            if (StringUtils.isEmpty(item)) {
                return false;
            }
        }
        return true;
    }
}
