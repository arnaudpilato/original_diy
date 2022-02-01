package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WorkshopController {
    @Autowired
    WorkshopRepository workshopRepository;

    @GetMapping("/workshop")
    public String getWorkshops(Model model) {
        model.addAttribute("workshops", workshopRepository.getAllWorkshops());

        return "/workshop/workshop";
    }
    
    @GetMapping("/workshop/{id}")
    public String getOneWorkshop(Model model, @PathVariable("id") Long id) {
        model.addAttribute("workshop", workshopRepository.getById(id));
        return "/workshop/oneWorkshop";
    }
}
