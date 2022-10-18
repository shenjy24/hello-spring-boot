package com.jonas.jpa.repository.mongo.bean.req;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/10/17 17:31
 * @description GameCreateReq
 */
@Data
public class GameCreateReq {
    private String id;
    private String name;
    private Map<String, List<String>> players = new HashMap<>();
    private String finishTime;
}
