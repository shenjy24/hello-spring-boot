package com.jonas.webflux.controller;

import com.jonas.webflux.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author shenjy
 */
@RestController
@RequestMapping("/webflux")
public class WebfluxController {

    @RequestMapping("/mono")
    public Mono<User> getUser(int id, String name) {
        User user = new User(id, name);
        return Mono.just(user);
    }
}
