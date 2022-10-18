package com.jonas.jpa.controller;

import com.jonas.jpa.repository.mongo.bean.req.GameCreateReq;
import com.jonas.jpa.repository.mongo.bean.view.GameView;
import com.jonas.jpa.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author shenjy
 * @createTime 2022/10/13 21:00
 * @description UserController
 */
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;

    @PostMapping("/create")
    public GameView createGame(@Valid @RequestBody GameCreateReq req) {
        return gameService.createGame(req);
    }
}
