package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkshopService {

    private final Date date = new Date();

    @Autowired
    WorkshopRepository workshopRepository;


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

}

