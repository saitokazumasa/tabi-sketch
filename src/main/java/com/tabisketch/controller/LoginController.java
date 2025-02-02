package com.tabisketch.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    @GetMapping
    public String get(final @AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails != null) return "redirect:/logout";

        return "login";
    }
}