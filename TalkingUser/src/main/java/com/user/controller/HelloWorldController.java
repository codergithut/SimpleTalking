package com.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by tianjian on 2019/3/3.
 */
@Controller
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "/webSocketDemo";
    }
}

