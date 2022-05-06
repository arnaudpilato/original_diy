package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import com.wildcodeschool.original_diy.repository.BadgeRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.request.BadgeRequest;
import com.wildcodeschool.original_diy.service.BadgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("api/test/badge")
public class BadgeController {
    @Autowired
    BadgeService badgeService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<DiyBadge>> getAllBadges(@RequestParam(name = "searchBadge") String searchBadge) {
        try {
            List<DiyBadge> badges = badgeService.getAllBadges(searchBadge);

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
            DiyBadge badge = badgeService.newBadge(badgeRequest);

            return new ResponseEntity<>(badge, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyBadge> getBadgeById(@PathVariable("id") long id) {
        try {
            Optional<DiyBadge> badge = badgeService.getBadgeById(id);

            return new ResponseEntity<>(badge.get(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyBadge> updateBadge(@PathVariable("id") long id, @RequestBody BadgeRequest badgeRequest) {
        try {
            Optional<DiyBadge> badge = badgeService.updateBadge(id, badgeRequest);

            return new ResponseEntity<>(badge.get(), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DiyBadge> deleteBadge(@PathVariable("id") Long id) {
        try {
            badgeService.deleteBadge(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
