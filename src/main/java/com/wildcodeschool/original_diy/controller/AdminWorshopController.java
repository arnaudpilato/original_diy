package com.wildcodeschool.original_diy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminWorshopController {
    @GetMapping("/admin/workshop")
    public String getWorkshops() {
        return "admin/worshop/worshop";
    }
}
