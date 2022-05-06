package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyFooter;
import com.wildcodeschool.original_diy.repository.FooterRepository;
import com.wildcodeschool.original_diy.request.FooterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FooterService {
    @Autowired
    FooterRepository footerRepository;

    public List<DiyFooter> getAllSocialNetworks() {
        List<DiyFooter> socialNetworks = new ArrayList<>();
        footerRepository.findAll().forEach(socialNetworks::add);

        return socialNetworks;
    }

    public DiyFooter createsocialNetwork(@Valid @RequestBody FooterRequest footerRequest) {
        DiyFooter footer = new DiyFooter();

        footer.setName(footerRequest.getName());
        footer.setSocialNetworkPath(footerRequest.getSocialNetworkPath());

        if (footerRequest.getPicturePath() == null) {
            footer.setPicturePath("/assets/img/social-network.png");
        } else {
            footer.setPicturePath(footerRequest.getPicturePath());
        }

        footer.setVisible(footerRequest.isVisible());

        footerRepository.save(footer);

        return footer;
    }

    @GetMapping("/get/{id}")
    public DiyFooter getSocialNetworkById(Long id) {
        Optional<DiyFooter> socialNetwork = footerRepository.findById(id);

        return socialNetwork.get();
    }

    public DiyFooter updateSocialNetwork(Long id, @RequestBody FooterRequest footerRequest) {
        Optional<DiyFooter> footerData = footerRepository.findById(id);
        DiyFooter footer = footerData.get();

        if (footerData.isPresent()) {
            footer.setName(footerRequest.getName());
            footer.setSocialNetworkPath(footerRequest.getSocialNetworkPath());

            if (footerRequest.getPicturePath() == null) {
                footer.setPicturePath(footer.getPicturePath());
            } else {
                footer.setPicturePath(footerRequest.getPicturePath());
            }

            footer.setVisible(footerRequest.isVisible());

            footerRepository.save(footer);
        }

        return footer;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteSocialNetwork(Long id) {
        footerRepository.deleteById(id);
    }
}
