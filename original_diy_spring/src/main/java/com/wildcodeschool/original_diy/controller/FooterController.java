package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import com.wildcodeschool.original_diy.repository.FooterRepository;
import com.wildcodeschool.original_diy.request.FooterRequest;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> createsocialNetwork(@Valid @RequestBody FooterRequest footerRequest) {
        try {
            DiyFooter footer = new DiyFooter();

            footer.setName(footerRequest.getName());
            footer.setSocialNetworkPath(footerRequest.getSocialNetworkPath());

            if (footerRequest.getPicturePath() == null) {
                footer.setPicturePath("/assets/img/static-picture.png");
            } else {
                footer.setPicturePath(footerRequest.getPicturePath());
            }

            footer.setVisible(footerRequest.isVisible());

            footerRepository.save(footer);

            return new ResponseEntity<>(footer, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyFooter> getSocialNetworkById(@PathVariable("id") long id) {
        Optional<DiyFooter> socialNetwork = footerRepository.findById(id);

        if (socialNetwork.isPresent()) {
            return new ResponseEntity<>(socialNetwork.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyFooter> updateSocialNetwork(@PathVariable("id") long id, @RequestBody FooterRequest footerRequest) {
        Optional<DiyFooter> footerData = footerRepository.findById(id);

        if (footerData.isPresent()) {
            DiyFooter footer = footerData.get();

            footer.setName(footerRequest.getName());

            if (footerRequest.getPicturePath() == null) {
                footer.setPicturePath(footer.getPicturePath());
            } else {
                footer.setPicturePath(footerRequest.getPicturePath());
            }

            footer.setVisible(footerRequest.isVisible());

            footerRepository.save(footer);

            return new ResponseEntity<>(footerRepository.save(footer), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSocialNetwork(@PathVariable("id") Long id) {
        try {
            footerRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
