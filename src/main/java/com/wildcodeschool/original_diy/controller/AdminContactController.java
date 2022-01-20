package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AdminContactController {
    @Autowired
    UserRepository userRepository;

    // PIL : Affichage des contacts
    @GetMapping("/admin/contact")
    public String getUsers(Model model) {
        model.addAttribute("users", userRepository.getAllUsers());
        return "admin/contact/contact";
    }

    // PIL : Ajout d'un contact
    @PostMapping("/admin/contact/add")
    public String addUser(@Valid DiyUser user, BindingResult result, @Param("password") String password) {
        if (result.hasErrors()) {

            return "admin/contact/new";
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "redirect:/admin/contact";
    }

    @GetMapping("/admin/contact/new")
    public String newUser(Model model) {
        DiyUser user = new DiyUser();

        model.addAttribute("user", user);

        return "admin/contact/new";
    }

    // PIL : Modification d'un contact
    @PostMapping("/admin/contact/update/{id}")
    public String postUser(@PathVariable("id") Long userId, @Valid DiyUser user, BindingResult result, @Param("password") String password) {
        if (result.hasErrors()) {
            user.setId(userId);

            return "admin/contact/update";
        }

        if(password == null || password.isEmpty()) {
            user.setPassword(userRepository.getById(user.getId()).getPassword());
        } else {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPassword = user.getPassword();
            String encodedPassword = encoder.encode(rawPassword);

            user.setPassword(encodedPassword);
        }

        userRepository.save(user);

        return "redirect:/admin/contact";
    }

    @GetMapping("/admin/contact/update/{id}")
    public String getUser(@PathVariable("id") Long id, Model model) {
        DiyUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id" + id));

        model.addAttribute("user", user);

        return "admin/contact/update";
    }

    // PIL : Suppression d'un contact
    @GetMapping("/admin/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        DiyUser user = userRepository.getById(id);
        userRepository.delete(user);

        return "redirect:/admin/contact";
    }
}
