package com.jonas.jpa.repository.mongo.bean.entity;

/**
 * @author shenjy
 * @createTime 2022/10/17 16:56
 * @description Game
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@Builder
@Document("game")
public class Game {
    @Id
    private String id;
    private String name;
    // <队伍名>:<玩家列表>
    private Map<String, List<Player>> teams = new HashMap<>();
    private LocalDateTime beginTime;
    private LocalDateTime finishTime;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Player {
        private String id;
        private String name;
    }
}
