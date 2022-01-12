package com.wildcodeschool.original_diy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/")
    public String index() {
        return "home/index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login.html";
    }
}
