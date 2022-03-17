package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyBackground;
import com.wildcodeschool.original_diy.repository.BackgroundRepository;
import com.wildcodeschool.original_diy.request.BackgroundRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/test/background")
public class BackgroundController {
    @Autowired
    BackgroundRepository backgroundRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<DiyBackground>> gettAllBackgrounds() {
        try {
            List<DiyBackground> backgrounds = new ArrayList<>();
            backgroundRepository.getAllBackgroundWithVisibility().forEach(backgrounds::add);

            if (backgrounds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(backgrounds, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> createBackground(@Valid @RequestBody BackgroundRequest backgroundRequest) {
        try {
            Optional<DiyBackground> backgroundData = backgroundRepository.findByname(backgroundRequest.getName());
            String s3 = "https://wcs-2-be-or-not-2-be.s3.eu-west-3.amazonaws.com/";
            DiyBackground background = backgroundData.get();

            background.setPicturePath(s3 + backgroundRequest.getPicturePath());
            background.setVisible(true);

            return new ResponseEntity<>(backgroundRepository.save(background), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyBackground> getBackground(@PathVariable("id") long id) {
        Optional<DiyBackground> background = backgroundRepository.findById(id);

        if (background.isPresent()) {
            return new ResponseEntity<>(background.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/visibility")
    public ResponseEntity<List<DiyBackground>> gettAllBackgroundsWithoutVisibility() {
        try {
            List<DiyBackground> backgrounds = new ArrayList<>();
            backgrounds.addAll(backgroundRepository.getAllBackgroundWithoutVisibility());

            if (backgrounds.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(backgrounds, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyBackground> updateBackground(@PathVariable("id") long id, @RequestBody BackgroundRequest backgroundRequest) {
        Optional<DiyBackground> backgroundData = backgroundRepository.findById(id);

        if (backgroundData.isPresent()) {
            DiyBackground background = backgroundData.get();

            if (backgroundRequest.getPicturePath() == null) {
                background.setPicturePath(null);
            } else {
                background.setPicturePath(backgroundRequest.getPicturePath());
            }

            return new ResponseEntity<>(backgroundRepository.save(background), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DiyBackground> deleteBackground(@PathVariable("id") Long id) {
        Optional<DiyBackground> backgroundData = backgroundRepository.findById(id);

        if (backgroundData.isPresent()) {
            DiyBackground background = backgroundData.get();

            background.setVisible(false);

            return new ResponseEntity<>(backgroundRepository.save(background), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
