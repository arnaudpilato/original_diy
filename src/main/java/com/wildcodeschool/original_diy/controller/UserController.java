package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "home/index";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @RequestMapping("/userCreate")
    public String postUser(@ModelAttribute DiyUser user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = user.getPassword();
        String encodedPassword = encoder.encode(rawPassword);

        user.setPassword(encodedPassword);

        try {
            user.setRole("USER");
            userRepository.save(user);
        } catch (Exception e) {
            return "user/user";
        }

        return "redirect:/";
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        DiyUser user = new DiyUser();
        model.addAttribute("user", user);

        return "user/user";
    }
}
