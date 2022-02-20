package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import com.wildcodeschool.original_diy.entity.DiyRole;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.model.ERole;
import com.wildcodeschool.original_diy.repository.FooterRepository;
import com.wildcodeschool.original_diy.request.FooterRequest;
import com.wildcodeschool.original_diy.request.UserRequest;
import com.wildcodeschool.original_diy.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @PostMapping("/new")
    public ResponseEntity<?> createsocialNetwork(@Valid @RequestBody FooterRequest footerRequest) {
        try {
            DiyFooter footer = new DiyFooter();

            footer.setName(footerRequest.getName());
            footer.setSocialNetworkPath(footerRequest.getSocialNetworkPath());
            footer.setPicturePath(footerRequest.getPicturePath());
            footer.setVisible(footerRequest.isVisible());

            footerRepository.save(footer);

            return new ResponseEntity<>(footer, HttpStatus.CREATED);
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
