package com.jonas.feature.event;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event/")
public class EventController {

    private final EventService eventService;

    @PostMapping("publish")
    public void publish(String source) {
        eventService.publishEvent(source);
    }
}
