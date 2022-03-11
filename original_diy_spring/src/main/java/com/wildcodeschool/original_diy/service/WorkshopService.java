package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkshopService {

    private Date date = new Date();

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
}
