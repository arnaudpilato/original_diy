package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyReservation;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.ReservationRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ReservationRepository reservationRepository;

    public void workshopControl(List<DiyWorkshop> workshops) {

        List<DiyWorkshop> workshopListHidden = workshops.stream().filter(workshop -> workshop.getDate().getTime() <
                date.getTime()).collect(Collectors.toList());

        for (DiyWorkshop workshop : workshopListHidden) {
            workshop.setConfirmation(false);
            workshopRepository.save(workshop);
        }
    }

    public void workshopReservation(DiyWorkshop workshop, DiyUser user) {

        try {

            if ((!workshop.getDiyUser().equals(user)) && (workshop.getReservation().getUser() == null)) {

                DiyReservation reservation = new DiyReservation();
                reservation.setWorkshop(workshop);
                reservation.setUser(user);
                reservation.setDate(date);
                reservationRepository.save(reservation);
            }

        } catch (Exception e) {
        }


    }
}
