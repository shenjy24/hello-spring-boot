package com.jonas.jpa.service;

import com.jonas.jpa.repository.mongo.bean.entity.Game;
import com.jonas.jpa.repository.mongo.bean.req.GameCreateReq;
import com.jonas.jpa.repository.mongo.bean.view.GameView;
import com.jonas.jpa.repository.mongo.dao.GameDao;
import com.jonas.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author shenjy
 * @createTime 2022/10/17 17:28
 * @description GameService
 */
@Service
@Validated
@RequiredArgsConstructor
public class GameService {
    private final GameDao gameDao;

    public GameView createGame(@Valid @RequestBody GameCreateReq req) {
        Map<String, List<Game.Player>> teams = new HashMap<>();
        for (Map.Entry<String, List<String>> entry : req.getPlayers().entrySet()) {
            List<Game.Player> players = entry.getValue().stream().map(e ->
                    new Game.Player(UUID.randomUUID().toString(), e)).collect(Collectors.toList());
            teams.put(entry.getKey(), players);
        }

        Game game = Game.builder()
                .id(UUID.randomUUID().toString()).name(req.getName()).teams(teams)
                .beginTime(LocalDateTime.now())
                .finishTime(DateUtils.string2DateTime(req.getFinishTime()))
                .build();
        gameDao.save(game);
        return buildGameView(game);
    }

    private GameView buildGameView(Game game) {
        GameView gameView = GameView.builder()
                .id(game.getId()).name(game.getName()).players(game.getTeams())
                .beginTime(game.getBeginTime()).finishTime(game.getFinishTime())
                .build();
        return gameView;
    }
}
