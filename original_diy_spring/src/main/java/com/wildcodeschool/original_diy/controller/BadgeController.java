package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import com.wildcodeschool.original_diy.repository.BadgeRepository;
import com.wildcodeschool.original_diy.request.BadgeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/test/badge")
public class BadgeController {
    @Autowired
    BadgeRepository badgeRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<DiyBadge>> gettAllBadges() {
        try {
            List<DiyBadge> badges = new ArrayList<>();
            badgeRepository.findAll().forEach(badges::add);

            if (badges.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(badges, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> createBadge(@Valid @RequestBody BadgeRequest badgeRequest) {
        try {
            DiyBadge badge = new DiyBadge();

            badge.setName(badgeRequest.getName());
            badge.setPicturePath(badgeRequest.getPicturePath());

            badgeRepository.save(badge);

            return new ResponseEntity<>(badge, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DiyBadge> deleteBadge(@PathVariable("id") Long id) {
        try {
            badgeRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
