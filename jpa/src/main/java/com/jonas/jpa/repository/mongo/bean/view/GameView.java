package com.jonas.jpa.repository.mongo.bean.view;

import com.jonas.jpa.repository.mongo.bean.entity.Game;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2022/10/17 17:29
 * @description GameView
 */
@Data
@Builder
public class GameView {
    private String id;
    private String name;
    // <队伍名>:<玩家列表>
    private Map<String, List<Game.Player>> players = new HashMap<>();
    private LocalDateTime beginTime;
    private LocalDateTime finishTime;
}
