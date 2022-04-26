package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyRole;
import com.wildcodeschool.original_diy.entity.DiySubCategory;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.model.ERole;
import com.wildcodeschool.original_diy.repository.RoleRepository;
import com.wildcodeschool.original_diy.repository.SubCategoryRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.WorkshopRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkshopService {

    private final Date date = new Date();

    @Autowired
    WorkshopRepository workshopRepository;

    @Autowired
    APIGouvService gouvService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;


    public void workshopControl(List<DiyWorkshop> workshops) {

        List<DiyWorkshop> workshopListHidden = workshops.stream().filter(workshop -> workshop.getDate().getTime() <
                date.getTime()).collect(Collectors.toList());

        for (DiyWorkshop workshop : workshopListHidden) {
            workshop.setConfirmation(false);
            workshopRepository.save(workshop);
        }
    }


    public void reservationWorkshop(DiyUser user, DiyWorkshop workshop) {
        try {

            // initialisation du liste de user
            List<DiyUser> users = new ArrayList<>();

            // j'ajoute dans la liste des ateliers les reservations qui sont représenter par une liste de users dans
            // l'entité workshop et j'ajoute ensuite le user actuel qui vien de reserver dans la liste des users
            users.addAll(workshop.getReservationUser());
            users.add(user);

            // j'effectue ensuite un controle qui vérifie si le user actuel et contenus dans la liste des reservations
            // de l'atelier, s'il n'est pas dans la liste alors on peut effectuer la reservation
            if ((!workshop.getReservationUser().contains(user))) {
                workshop.setReservationUser(users);
                workshopRepository.save(workshop);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void reservationDelete(DiyUser user, DiyWorkshop workshop) {
        try {
            workshop.getReservationUser().remove(user);
            workshopRepository.save(workshop);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void createWorkshop(@Valid @RequestBody WorkshopRequest workshopRequest, DiyWorkshop workshop, DiyUser user) {

        workshop.setTitle(workshopRequest.getTitle());

        if (workshopRequest.getPicturePath() == null) {
            workshop.setPicturePath("/assets/img/static-picture.png");
        } else {
            workshop.setPicturePath(workshopRequest.getPicturePath());
        }

        workshop.setStreetNumber(workshopRequest.getStreetNumber());
        workshop.setStreet(workshopRequest.getStreet());
        workshop.setPostCode(workshopRequest.getPostCode());
        workshop.setCity(workshopRequest.getCity());
        workshop.setDescription(workshopRequest.getDescription());


       Optional<DiySubCategory> subCategory = subCategoryRepository.findById(workshopRequest.getSubCategoryId());
        System.out.println("test");
        if (subCategory.isEmpty()) {
          // EXECPTION
        }
        System.out.println("test2");
        DiySubCategory subCategoryReal = subCategory.get();
        System.out.println("test3");
        workshop.setSubCategorycategorie(subCategoryReal);
        System.out.println("test4");
        Set<DiyRole> roles = new HashSet<>();
        roles = user.getRoles();
        System.out.println("test4");
        for (DiyRole role : roles
        ) {
            if (role.getName() == ERole.ROLE_ADMIN) {
                workshop.setConfirmation(true);
            } else {
                workshop.setConfirmation(false);
            }
        }
        System.out.println("zebi7");
        double latitude = gouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(1).asDouble();
        double longitude = gouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(0).asDouble();

        workshop.setLatitude(latitude);
        workshop.setLongitude(longitude);
        System.out.println("zebi8");
        workshop.setDiyUser(workshopRequest.getDiyUser());
        workshop.setDate(workshopRequest.getDate());
        workshopRepository.save(workshop);

    }
}

