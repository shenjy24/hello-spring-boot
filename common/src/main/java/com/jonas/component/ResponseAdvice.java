package com.jonas.component;

import com.jonas.bean.JsonResult;
import com.jonas.bean.SystemCode;
import com.jonas.bean.access.AccessLog;
import com.jonas.bean.access.CurrentLog;
import com.jonas.util.GsonUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author shenjy
 * @date 2020/8/13
 * @description 响应结果封装
 */
@ControllerAdvice
@ConditionalOnMissingClass
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returenType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returenType, MediaType mediaType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        AccessLog accessLog = CurrentLog.accessLog.get();
        accessLog.setBody(body);

        if (!(body instanceof JsonResult)) {
            if (!(body instanceof String)) {
                return new JsonResult(SystemCode.SUCCESS, body);
            } else {
                return GsonUtils.toJson(new JsonResult(SystemCode.SUCCESS, body));
            }
        }
        return body;
    }
}
