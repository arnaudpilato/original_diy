package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyBadge;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.repository.BadgeRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.request.BadgeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/test/badge")
public class BadgeController {
    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    UserRepository userRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<DiyBadge>> getAllBadges(@RequestParam(name = "searchBadge") String searchBadge) {
        try {
            List<DiyBadge> badges = new ArrayList<>();

            if (searchBadge != null) {
                badgeRepository.getBadgesByFilter(searchBadge).forEach(badges::add);
            } else {
                badgeRepository.findAll().forEach(badges::add);
            }


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
            badge.setDescription(badgeRequest.getDescription());

            if (badgeRequest.getCondition().equals("now")) {
                badge.setStep(0);
            } else {
                badge.setStep(badgeRequest.getStep());
            }

            badgeRepository.save(badge);




            if (badgeRequest.getPeoples().length > 0) {
                System.out.println("Elle est bonne la condition");
                for (Long userId : badgeRequest.getPeoples()) {
                    System.out.println("condition for");
                    DiyUser user = userRepository.getById(userId);
                    System.out.println(userId);

                    List<DiyBadge> badgeUser = new ArrayList<>();
                    badgeUser.addAll(user.getBadges());
                    System.out.println(badgeUser);
                    badgeUser.add(badge);
                    System.out.println(badgeUser);
                    System.out.println(user);

                    user.setBadges(badgeUser);
                    userRepository.save(user);
                    System.out.println(badgeUser);
                }
            }

            return new ResponseEntity<>(badge, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyBadge> getBadgeById(@PathVariable("id") long id) {
        Optional<DiyBadge> badge = badgeRepository.findById(id);

        if (badge.isPresent()) {
            return new ResponseEntity<>(badge.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyBadge> updateBadge(@PathVariable("id") long id, @RequestBody BadgeRequest badgeRequest) {
        Optional<DiyBadge> badgeData = badgeRepository.findById(id);

        if (badgeData.isPresent()) {
            DiyBadge badge = badgeData.get();

            badge.setName(badgeRequest.getName());

            if (badgeRequest.getPicturePath() == null) {
                badge.setPicturePath(badge.getPicturePath());
            } else {
                badge.setPicturePath(badgeRequest.getPicturePath());
            }

            badge.setDescription(badgeRequest.getDescription());

            return new ResponseEntity<>(badgeRepository.save(badge), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
