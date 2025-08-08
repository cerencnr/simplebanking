package com.eteration.simplebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class IndexController {
    @GetMapping
    public RedirectView redirectToSwagger() {
        return new RedirectView("http://localhost:8080/swagger-ui.html");
    }
}
