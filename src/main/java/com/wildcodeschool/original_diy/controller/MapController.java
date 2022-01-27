package com.wildcodeschool.original_diy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wildcodeschool.original_diy.entity.APIGouvAdress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Controller
public class MapController {

    private static final String API_MAP_URL = "https://api-adresse.data.gouv.fr/search/";

    @GetMapping("/map")
    public String index() {
        // String url = "https://api-adresse.data.gouv.fr/search/?q=178+allee+adrienne+bolland&postcode=45770";

        return "map/map";
    }
}
