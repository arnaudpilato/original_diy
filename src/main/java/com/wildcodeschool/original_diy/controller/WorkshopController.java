package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class WorkshopController {
    @Autowired
    WorkshopRepository workshopRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/workshop")
    public String getWorkshops(Model model) {
        model.addAttribute("workshops", workshopRepository.getAllWorkshops());

        return "/workshop/workshop";
    }

    @GetMapping("/workshop/{id}")
    public String getOneWorkshop(Model model, @PathVariable("id") Long id, Principal principal) {
        DiyComment diyComment = new DiyComment();
        model.addAttribute("workshop", workshopRepository.getById(id));
        model.addAttribute("comment", diyComment);
        if (principal != null) {
            DiyUser user = userRepository.getByUsername(principal.getName());
            model.addAttribute("user", user);
        } else {
            model.addAttribute("user", "null");
        }

        return "/workshop/oneWorkshop";
    }
}
