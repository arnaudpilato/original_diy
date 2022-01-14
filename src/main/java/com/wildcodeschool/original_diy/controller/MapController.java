package com.wildcodeschool.original_diy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {
    @GetMapping("/map")
    public String index() {
        return "home/index";
    }
}
