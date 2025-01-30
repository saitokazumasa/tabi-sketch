package com.tabisketch.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan/{uuid}/edit")
public class EditPlanController {
    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    @GetMapping
    public String get(final @PathVariable String uuid, Model model) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        return "plan/edit";
    }
}
