package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class AdminContactController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/admin/contact")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.getAllUsers());
        return "admin/contact/contact";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        DiyUser user = userRepository.getById(id);
        userRepository.delete(user);

        return "redirect:/admin/contact";
    }

    @PostMapping("/admin/contact/update/{id}")
    public String postUser(@PathVariable("id") Long userId, @Valid DiyUser user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(userId);

            return "admin/contact/update";
        }

        userRepository.save(user);

        return "redirect:/admin/contact";
    }

    @GetMapping("/admin/contact/edit/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        DiyUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id" + id));

        model.addAttribute("user", user);

        return "admin/contact/update";
    }
}