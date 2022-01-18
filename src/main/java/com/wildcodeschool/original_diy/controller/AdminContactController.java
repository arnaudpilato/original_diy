package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class AdminContactController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/contact")
    public String getContact(Model model) {
        model.addAttribute("users", userRepository.getAllUsers());
        return "admin/contact/contact";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Principal principal) {
        DiyUser user = userRepository.getById(id);
        userRepository.delete(user);

        return "redirect:/admin/contact";
    }
}
