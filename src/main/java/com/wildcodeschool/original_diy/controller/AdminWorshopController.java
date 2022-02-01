package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.entity.DiyWorkshopUser;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.repository.WorkshopUserRepository;
import com.wildcodeschool.original_diy.service.APIGouvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
public class AdminWorshopController {
    @Autowired
    public WorkshopRepository workshopRepository;
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public WorkshopUserRepository workshopUserRepository;
    @Autowired
    public APIGouvService APIGouvService;

    // PIL : Affichage des ateliers
    @GetMapping("/admin/workshop")
    public String getWorkshops(Model model) {
        model.addAttribute("worshops", workshopRepository.getAllWorkshops());

        return "admin/workshop/workshop";
    }

    // PIL : Ajout d'un nouvel atelier
    @RequestMapping("/admin/workshop/add")
    public String addWorkshop(@ModelAttribute DiyWorkshop workshop,
                              @RequestParam(value = "picture_file") MultipartFile picture,
                              Principal principal, Model model) throws IOException {
        if (!picture.isEmpty()) {
            String filename = "/static/data/" + picture.getOriginalFilename();
            Files.copy(picture.getInputStream(), Paths.get("src/main/resources/public/static/data/" +
                    picture.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            workshop.setPicture(filename);

        } else if (workshop.getId() != null){
            workshop.setPicture(workshopRepository.getById(workshop.getId()).getPicture());
        } else {
            workshop.setPicture("/static/img/static-picture.png");
        }
        try {

            double latitude = APIGouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                    workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(1).asDouble();
            double longitude = APIGouvService.getAdressAsJson(workshop.getStreet(), workshop.getPostCode(),
                    workshop.getStreetNumber()).get("features").get(0).get("geometry").get("coordinates").get(0).asDouble();

            System.out.println(" latitude : " + latitude);
            System.out.println(" longitude : " + longitude);

            workshop.setLatitude(latitude);
            workshop.setLongitude(longitude);

            System.out.println("data set");
            workshopRepository.save(workshop);
            System.out.println("data save");
            DiyUser user = userRepository.getByUsername(principal.getName());
            DiyWorkshopUser diyWorkshopUser = new DiyWorkshopUser(user, workshop);
            workshopUserRepository.save(diyWorkshopUser);

            model.addAttribute("workshops", workshopRepository.getAllWorkshops());
            System.out.println("Dans le try");
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("dans le catch");
        }


        return "redirect:/admin/workshop";

    }

    @GetMapping("/admin/workshop/new")
    public String newWorkshop(Model model, @RequestParam(required = false, value = "id") Long id) {
        DiyWorkshop workshop = new DiyWorkshop();
        if (id != null) {
            workshop = workshopRepository.getById(id);
        }
        model.addAttribute("workshop", workshop);

        return "/admin/workshop/new";
    }

    @GetMapping("/admin/workshop/delete/{id}")
    public String deleteWorkshop(@PathVariable("id") Long id) {
        DiyWorkshop workshop = workshopRepository.getById(id);
        workshopRepository.delete(workshop);

        return "redirect:/admin/workshop";
    }
}
