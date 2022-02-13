package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/workshop/")
public class WorkshopController {
    @Autowired
    WorkshopRepository workshopRepository;

    @GetMapping("all")
    public ResponseEntity<List<DiyWorkshop>> getAllWorkshops() {
        try {
            List<DiyWorkshop> workshops = new ArrayList<>();
            workshopRepository.findAll().forEach(workshops::add);

            if (workshops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    /*
    @Autowired
    WorkshopRepository workshopRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/workshop")
    public String getWorkshops(Model model) {
        model.addAttribute("workshops", workshopRepository.getAllConfirmedWorkshops());

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
    }*/
}