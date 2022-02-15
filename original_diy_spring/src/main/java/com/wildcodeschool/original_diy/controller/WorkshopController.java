package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyRole;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.model.ERole;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.WorkshopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.*;

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

    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyWorkshop> updateWorkshop(@PathVariable("id") long id, @RequestBody WorkshopRequest workshopRequest) {
        Optional<DiyWorkshop> workshopData = workshopRepository.findById(id);

        if (workshopData.isPresent()) {
            DiyWorkshop workshop = workshopData.get();

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

            return new ResponseEntity<>(workshopRepository.save(workshop), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        try {
            workshopRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}