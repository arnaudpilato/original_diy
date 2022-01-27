package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
public class AdminWorshopController {
    @Autowired
    public WorkshopRepository workshopRepository;
    @Autowired
    public UserRepository userRepository;

    // PIL : Affichage des ateliers
    @GetMapping("/admin/workshop")
    public String getWorkshops(Model model) {
        model.addAttribute("worshops", workshopRepository.getAllWorkshops());

        return "admin/workshop/workshop";
    }

    // PIL : Ajout d'un nouvel atelier
    @PostMapping("/admin/workshop/add")
    public String addWorkshop(@ModelAttribute DiyWorkshop workshop,
                              @RequestParam(value = "picture_file") MultipartFile picture,
                              Principal principal) throws IOException {
        if (!picture.isEmpty()) {
            String filename = "/static/data/" + picture.getOriginalFilename();
            Files.copy(picture.getInputStream(), Paths.get("src/main/resources/public/static/data/" +
                    picture.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            workshop.setPicture(filename);
        } else {
            workshop.setPicture("/static/img/static-picture.png");
        }

        DiyUser currentUser = userRepository.getByUsername(principal.getName());
        workshopRepository.save(workshop);


        return "redirect:/admin/workshop";
    }

    @GetMapping("/admin/workshop/new")
    public String newWorkshop(Model model) {
        DiyWorkshop workshop = new DiyWorkshop();
        model.addAttribute("workshop", workshop);

        return "/admin/workshop/new";
    }

    @GetMapping("/admin/workshop/delete/{id}")
    public String deleteWorkshop(@PathVariable("id") Long id) {
        DiyWorkshop workshop = workshopRepository.getById(id);
        workshopRepository.delete(workshop);

        return "redirect:/admin/workshop";
    }
}
