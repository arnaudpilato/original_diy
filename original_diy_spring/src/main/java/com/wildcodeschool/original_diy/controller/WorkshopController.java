package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.WorkshopRequest;
import com.wildcodeschool.original_diy.service.APIGouvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/workshop")
public class WorkshopController {

    @Autowired
    WorkshopRepository workshopRepository;

    @Autowired
    APIGouvService gouvService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<List<DiyWorkshop>> getAllWorkshops() {
        try {
            List<DiyWorkshop> workshops = new ArrayList<>();
            workshops.addAll(workshopRepository.findAll());

            if (workshops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyWorkshop> getWorkshopById(@PathVariable("id") long id) {
        Optional<DiyWorkshop> workshop = workshopRepository.findById(id);

        if (workshop.isPresent()) {
            return new ResponseEntity<>(workshop.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
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

            double latitude = gouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                    workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(1).asDouble();
            double longitude = gouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                    workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(0).asDouble();

            workshop.setLatitude(latitude);
            workshop.setLongitude(longitude);
            workshop.setDiyUser(workshopRequest.getDiyUser());
            workshopRepository.save(workshop);
            return new ResponseEntity<>(workshop, HttpStatus.CREATED);

        } catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteWorkshop(@PathVariable("id") Long id) {
        try {
            workshopRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}