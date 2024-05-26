package com.example.firstservice.sample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class FirstServiceController {

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to the first service. ";
    }
}
