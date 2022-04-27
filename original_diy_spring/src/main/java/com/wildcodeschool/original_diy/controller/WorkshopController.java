package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.DTO.WorkshopDTO;
import com.wildcodeschool.original_diy.entity.*;
import com.wildcodeschool.original_diy.repository.*;

import com.wildcodeschool.original_diy.request.WorkshopRequest;
import com.wildcodeschool.original_diy.service.APIGouvService;
import com.wildcodeschool.original_diy.service.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/workshop")
public class WorkshopController {

    @Autowired
    WorkshopRepository workshopRepository;
    @Autowired
    APIGouvService gouvService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    WorkshopService workshopService;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    BadgeRepository badgeRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @PreAuthorize("permitAll()")
    @GetMapping("/all")
    public ResponseEntity<List<DiyWorkshop>> getAllWorkshops() {
        try {
            List<DiyWorkshop> workshops = new ArrayList<>();
            workshops.addAll(workshopRepository.findAll());

            if (workshops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/allConfirmed")
    public ResponseEntity<List<DiyWorkshop>> getAllWorkshopsConfirmed() {
        try {
            List<DiyWorkshop> workshops = new ArrayList<>();

            workshops.addAll(workshopRepository.getAllConfirmedWorkshops());
            workshopService.workshopControl(workshops);

            List<DiyWorkshop> workshopsNew = new ArrayList<>();
            workshopsNew.addAll(workshopRepository.getAllConfirmedWorkshops());

            if (workshops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshopsNew, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/last-workshops")
    public ResponseEntity<List<WorkshopDTO>> getLastWorkshops(Authentication authentication) {

        try {
            List<DiyWorkshop> workshops = new ArrayList<>();
            workshops.addAll(workshopRepository.getThreeLastWorkshops());
            workshopService.workshopControl(workshops);

            List<DiyWorkshop> workshopsNew = new ArrayList<>();
            workshopsNew.addAll(workshopRepository.getThreeLastWorkshops());

            List<WorkshopDTO> workshopsDTO = new ArrayList<>();

            for (DiyWorkshop workshop : workshopsNew
            ) {
                WorkshopDTO workshopDTO = new WorkshopDTO(workshop.getId(), workshop.getReservationUser(),
                        workshop.getDate(), workshop.getDescription(), workshop.getTitle(),
                        workshop.getPicturePath(), workshop.getLimitedPlaces(), workshop.getSubCategory(),
                        workshop.getSubCategory().getCategory());
                workshopsDTO.add(workshopDTO);
            }
            if (workshops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshopsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/worskhop-by-user")
    public ResponseEntity<List<DiyWorkshop>> getWorkshopByUserId(Authentication authentication) {
        DiyUser user = userRepository.getUserByUsername(authentication.getName());
        try {
            user.getUsername().equals(authentication.getName());

            List<DiyWorkshop> workshops = new ArrayList<>();

            workshops.addAll(workshopRepository.getDiyWorkshopByDiyUserId(user));


            if (workshops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("permitAll()")
    @GetMapping("/get/{id}")
    public ResponseEntity<DiyWorkshop> getWorkshopById(@PathVariable("id") long id, Authentication authentication) {
        Optional<DiyWorkshop> workshop = workshopRepository.findById(id);


        if (workshop.isPresent()) {
            return new ResponseEntity<>(workshop.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/get-atelier/{id}")
    public ResponseEntity<DiyWorkshop> getWorkshopByIdHome(@PathVariable("id") long id) {
        Optional<DiyWorkshop> workshop = workshopRepository.findById(id);

        if (workshop.isPresent()) {
            return new ResponseEntity<>(workshop.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/new")
    public ResponseEntity<?> createWorkshopUserAndAdmin(@Valid @RequestBody WorkshopRequest workshopRequest,
                                                        Authentication authentication) {

        try {
            DiyWorkshop workshop = new DiyWorkshop();
            DiyUser user = userRepository.getUserByUsername(authentication.getName());
            workshopService.createWorkshop(workshopRequest, workshop, user);
            return new ResponseEntity<>(workshop, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DiyWorkshop> updateWorkshop(@PathVariable("id") long id, @RequestBody WorkshopRequest workshopRequest) {
        Optional<DiyWorkshop> workshopData = workshopRepository.findById(id);

        if (workshopData.isPresent()) {
            DiyWorkshop workshop = workshopData.get();

            workshop.setTitle(workshopRequest.getTitle());

            if (workshopRequest.getPicturePath() == null) {
                workshop.setPicturePath(workshop.getPicturePath());
            } else {
                workshop.setPicturePath(workshopRequest.getPicturePath());
            }

            workshop.setStreetNumber(workshopRequest.getStreetNumber());
            workshop.setStreet(workshopRequest.getStreet());
            workshop.setPostCode(workshopRequest.getPostCode());
            workshop.setCity(workshopRequest.getCity());
            workshop.setDescription(workshopRequest.getDescription());
            workshop.setConfirmation(workshopRequest.isConfirmation());
            workshop.setDate(workshopRequest.getDate());
            workshop.setLimitedPlaces(workshopRequest.getLimitedPlaces());
            if (workshopRequest.getSubCategoryId() == 0) {
                workshop.setSubCategory(workshop.getSubCategory());
            } else {
                workshop.setSubCategory(subCategoryRepository.getById(workshopRequest.getSubCategoryId()));
            }

            workshopRepository.save(workshop);

            return new ResponseEntity<>(workshopRepository.save(workshop), HttpStatus.OK);
        } else {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteWorkshop(@PathVariable("id") Long id) {
        try {
            List<DiyComment> commentList = workshopRepository.getById(id).getComments();
            for (DiyComment comment : commentList
            ) {
                commentRepository.deleteById(comment.getId());
            }
            workshopRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PatchMapping("worskhop/reservation/{id}")
    public ResponseEntity<HttpStatus> workshopReservation(@PathVariable("id") Long id, Authentication authentication) {
        try {

            DiyUser user = userRepository.getUserByUsername(authentication.getName());
            DiyWorkshop workshop = workshopRepository.getById(id);

            workshopService.reservationWorkshop(user, workshop);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("reservation/delete/{id}")
    public ResponseEntity<HttpStatus> workshopReservationDelete(@PathVariable("id") Long id,
                                                                Authentication authentication) {
        try {
            DiyUser user = userRepository.getUserByUsername(authentication.getName());
            DiyWorkshop workshop = workshopRepository.getById(id);

            workshopService.reservationDelete(user, workshop);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("reservation/deleteByUsername/{username}/{workshopId}")
    public ResponseEntity<HttpStatus> workshopReservationDeleteByUsername(@PathVariable("username") String username, @PathVariable("workshopId") Long workshopId,
                                                                          Authentication authentication) {
        try {
            DiyUser user = userRepository.getUserByUsername(username);
            DiyWorkshop workshop = workshopRepository.getById(workshopId);

            workshopService.reservationDelete(user, workshop);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/reservation-by-current-user")
    public ResponseEntity<List<DiyWorkshop>> workshopReservationByCurrentUser(Authentication authentication) {
        try {
            DiyUser user = userRepository.getUserByUsername(authentication.getName());

            List<DiyWorkshop> workshops = workshopRepository.getDiyWorkshopByReservationUser(user.getId());

            if (workshops.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(workshops, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/all/category")
    public ResponseEntity<List<DiyCategory>> getAllCategories() {
        try {

            List<DiyCategory> categories = new ArrayList<>();

            categories = categoryRepository.findAll();

            if (categories.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
