package com.tabisketch.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan/create")
public class CreatePlanController {
    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    @GetMapping
    public String get(
            final @AuthenticationPrincipal(expression = "username") String mailAddress,
            final Model model
    ) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        return "plan/present-create";
    }
}
