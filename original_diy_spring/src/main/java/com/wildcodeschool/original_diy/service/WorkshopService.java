package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.dto.WorkshopDTO;
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
            if (users.size() < workshop.getLimitedPlaces()) {
                users.add(user);

                // j'effectue ensuite un controle qui vérifie si le user actuel et contenus dans la liste des reservations
                // de l'atelier, s'il n'est pas dans la liste alors on peut effectuer la reservation
                if ((!workshop.getReservationUser().contains(user))) {
                    workshop.setReservationUser(users);
                    workshopRepository.save(workshop);
                }
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

        if (subCategory.isEmpty()) {
          // EXECPTION
        }

        DiySubCategory subCategoryReal = subCategory.get();
        workshop.setSubCategory(subCategoryReal);
        if ((workshopRequest.getLimitedPlaces() == null) || (workshopRequest.getLimitedPlaces() < 1)) {
            workshop.setLimitedPlaces(1L);
        } else {
            workshop.setLimitedPlaces(workshopRequest.getLimitedPlaces());
        }

        Set<DiyRole> roles = new HashSet<>();
        roles = user.getRoles();
        for (DiyRole role : roles
        ) {
            if (role.getName() == ERole.ROLE_ADMIN) {
                workshop.setConfirmation(true);
            } else {
                workshop.setConfirmation(false);
            }
        }
        double latitude = gouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(1).asDouble();
        double longitude = gouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(0).asDouble();

        workshop.setLatitude(latitude);
        workshop.setLongitude(longitude);

        workshop.setDiyUser(workshopRequest.getDiyUser());
        workshop.setDate(workshopRequest.getDate());
        workshopRepository.save(workshop);
    }


    public List<WorkshopDTO> showWorkshopDTO (){
        List<DiyWorkshop> workshops = new ArrayList<>();
        workshops.addAll(workshopRepository.getThreeLastWorkshops());
        workshopControl(workshops);

        List<DiyWorkshop> workshopsNew = new ArrayList<>();
        workshopsNew.addAll(workshopRepository.getThreeLastWorkshops());

        List<WorkshopDTO> workshopsSortDTO = new ArrayList<>();

        for (DiyWorkshop workshop : workshopsNew
        ) {
            WorkshopDTO workshopDTO = new WorkshopDTO(workshop.getId(), workshop.getReservationUser(),
                    workshop.getDate(), workshop.getDescription(), workshop.getTitle(),
                    workshop.getPicturePath(), workshop.getLimitedPlaces(), workshop.getSubCategory(),
                    workshop.getSubCategory().getCategory());
            workshopsSortDTO.add(workshopDTO);
        }
        return workshopsSortDTO;
    }

    public List<WorkshopDTO> showWorkshopConfirmedDTO (){
        List<DiyWorkshop> workshops = new ArrayList<>();

        workshops.addAll(workshopRepository.getAllConfirmedWorkshops());
        workshopControl(workshops);

        List<DiyWorkshop> workshopsNew = new ArrayList<>();
        workshopsNew.addAll(workshopRepository.getAllConfirmedWorkshops());

        List<WorkshopDTO> workshopsSortDTO = new ArrayList<>();

        for (DiyWorkshop workshop : workshopsNew
        ) {
            WorkshopDTO workshopDTO = new WorkshopDTO(workshop.getId(), workshop.getReservationUser(),
                    workshop.getDate(), workshop.getDescription(), workshop.getTitle(),
                    workshop.getPicturePath(), workshop.getLimitedPlaces(), workshop.getSubCategory(),
                    workshop.getSubCategory().getCategory(), workshop.getStreetNumber(), workshop.getStreet(),
                    workshop.getPostCode(),workshop.getCity(), workshop.getLongitude(), workshop.getLatitude(), workshop.getDiyUser());
            workshopsSortDTO.add(workshopDTO);
        }
        return workshopsSortDTO;
    }

}

