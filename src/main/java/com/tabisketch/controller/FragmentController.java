package com.tabisketch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FragmentController {
    @GetMapping("/fragment/modal/places")
    public String modalPlaces(@RequestParam("num") int num, Model model) {
        model.addAttribute("num", num);
        return "fragment/modal :: places";
    }
}
