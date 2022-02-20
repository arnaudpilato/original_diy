package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import com.wildcodeschool.original_diy.entity.DiyUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/test/footer")
public class FooterController {
    @GetMapping("/all")
    public ResponseEntity<List<DiyFooter>> getAllSocialNetworks() {
        try {
            List<DiyFooter> socialNetworks = new ArrayList<>();
            footerRepository.findAll().forEach(socialNetworks::add);

            if (socialNetworks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(socialNetworks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
