package com.wildcodeschool.original_diy.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RedirectionController implements ErrorController {

    @GetMapping(value = "/error")
    public String handleError(HttpServletRequest request) {
        // PIL : Récupération des status d'erreurs
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // TODO: log error details here

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            // PIL : Redirection suivant le code erreur
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                return "redirection/404";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "redirection/500";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                return "redirection/403";
            }
        }

        // PIL : Page par défaut si erreur non reconnue
        return "redirection/error";
    }
}
