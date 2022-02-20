package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import com.wildcodeschool.original_diy.repository.FooterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("api/test/footer")
public class FooterController {
    @Autowired
    FooterRepository footerRepository;

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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<HttpStatus> deleteSocialNetwork(@PathVariable("id") Long id) {
        try {
            footerRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
