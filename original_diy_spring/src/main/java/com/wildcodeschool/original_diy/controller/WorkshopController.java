package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.WorkshopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/workshop")
public class WorkshopController {
    @Autowired
    WorkshopRepository workshopRepository;

    @GetMapping("/all")
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

    @PostMapping("/new")
    public ResponseEntity<?> createWorkshop(@Valid @RequestBody WorkshopRequest workshopRequest) {
        try {
            DiyWorkshop workshop = new DiyWorkshop();

            workshop.setTitle(workshopRequest.getTitle());

            if (workshopRequest.getPicturePath() == null) {
                workshop.setPicturePath("/assets/img/static-picture.png");
            } else {
                workshop.setPicturePath(workshopRequest.getPicturePath());
            }

            workshop.setStreetNumber(workshopRequest.getStreetNumber());
            workshop.setStreet(workshopRequest.getStreet());
            workshop.setPostCode(workshopRequest.getPostCode());
            workshop.setCity(workshopRequest.getCity());
            workshop.setDescription(workshopRequest.getDescription());
            workshop.setConfirmation(workshopRequest.isConfirmation());


            workshopRepository.save(workshop);

            return new ResponseEntity<>(workshop, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}