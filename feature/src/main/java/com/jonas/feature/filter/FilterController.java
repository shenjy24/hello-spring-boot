package com.jonas.feature.filter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filter")
public class FilterController {

    @RequestMapping("/testFilter")
    public void testFilter(String val) {
        System.out.println("testFilter");
    }
}
