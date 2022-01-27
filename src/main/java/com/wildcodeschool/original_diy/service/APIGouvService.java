package com.wildcodeschool.original_diy.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class APIGouvService {
    private static final String API_MAP_URL = "https://api-adresse.data.gouv.fr/search/";
    private WebClient webClient;

    private WebClient getWebClient() {
        if (webClient == null) {
            webClient = WebClient.create(API_MAP_URL);
        }

        return webClient;
    }

    public JsonNode getAdressAsJson(String street, Long postcode, Long streetNumber) {
        try {
            return new ObjectMapper().readTree(getAdressAPI(street, postcode, streetNumber));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public String getAdressAPI(String street, Long postcode, Long streetNumber) {
        // String url = "https://api-adresse.data.gouv.fr/search/?q=178+allee+adrienne+bolland&postcode=45770";
       return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", streetNumber, "+", street + "&")
                        .queryParam("postcode", postcode)
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class).block();


    }

}
