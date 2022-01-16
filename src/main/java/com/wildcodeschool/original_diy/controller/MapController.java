package com.wildcodeschool.original_diy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Controller
public class MapController {

    @GetMapping("/map")
    public String index() {
        String url = "https://api-adresse.data.gouv.fr/search/?q=178+allee+adrienne+bolland&postcode=45770";
        WebClient webClient = WebClient.create(url);
        Mono<String> call = webClient.get()
                .retrieve()
                .bodyToMono(String.class);
        String response = call.block();

        System.out.println(response);
        return "map/map";

    }
}
