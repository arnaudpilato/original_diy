package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import com.wildcodeschool.original_diy.repository.FooterRepository;
import com.wildcodeschool.original_diy.request.FooterRequest;
import com.wildcodeschool.original_diy.service.FooterService;
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
@RequestMapping("api/test/footer")
public class FooterController {
    @Autowired
    FooterRepository footerRepository;

    @Autowired
    FooterService footerService;

    @GetMapping("/all")
    public ResponseEntity<List<DiyFooter>> getAllSocialNetworks() {
        try {
            List<DiyFooter> socialNetworks = footerService.getAllSocialNetworks();

            if (socialNetworks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(socialNetworks, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> createsocialNetwork(@Valid @RequestBody FooterRequest footerRequest) {
        try {
            DiyFooter footer = footerService.createsocialNetwork(footerRequest);

            return new ResponseEntity<>(footer, HttpStatus.CREATED);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyFooter> getSocialNetworkById(@PathVariable("id") long id) {
        try {
            DiyFooter socialNetwork = footerService.getSocialNetworkById(id);

            return new ResponseEntity<>(socialNetwork, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyFooter> updateSocialNetwork(@PathVariable("id") long id, @RequestBody FooterRequest footerRequest) {
        try {
            DiyFooter footer = footerService.updateSocialNetwork(id, footerRequest);

            return new ResponseEntity<>(footerRepository.save(footer), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSocialNetwork(@PathVariable("id") Long id) {
        try {
            footerService.deleteSocialNetwork(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
