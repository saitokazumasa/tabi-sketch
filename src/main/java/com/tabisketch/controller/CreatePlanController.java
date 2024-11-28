package com.tabisketch.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan/create")
public class CreatePlanController {
    @GetMapping
    public String get() {
        return "plan/create";
    }
}
