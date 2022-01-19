package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminWorshopController {
    @Autowired
    WorkshopRepository workshopRepository;

    @GetMapping("/admin/workshop")
    public String getWorkshops() {
        return "admin/workshop/workshop";
    }

    @GetMapping("/admin/workshop/add")
    public String addWorkshop(Model model) {
        DiyWorkshop workshop = new DiyWorkshop();
        model.addAttribute("workshop", workshop);

        return "/admin/workshop/add";
    }
}
