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

    @RequestMapping("/addUser")
    public String addUser(@ModelAttribute("user") DiyUser user, BindingResult result, @Param("username") String username, @Param("password") String password) {
        if (username.isEmpty()) {
            result.rejectValue("username", "isEmpty", "Le champ nom est vide !");
            result.hasErrors();
        }

        if (password.isEmpty()) {
            result.hasErrors();
            result.rejectValue("password", "isEmpty", "Le champ mot de passe est vide !");
        }

        if (result.hasErrors()) {
            return "user/user";
        } else {
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
    }

    @GetMapping("/user")
    public String getUser(Model model) {
        DiyUser user = new DiyUser();
        model.addAttribute("user", user);

        return "user/user";
    }
}
