/*package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {
    @Autowired
    public WorkshopRepository workshopRepository;

    private static final String API_MAP_URL = "https://api-adresse.data.gouv.fr/search/";

    @GetMapping("/map")
    public String index(Model model) {
        // String url = "https://api-adresse.data.gouv.fr/search/?q=178+allee+adrienne+bolland&postcode=45770";
    model.addAttribute("workshops", workshopRepository.getAllWorkshops());
        return "map/map";
    }
}
*/