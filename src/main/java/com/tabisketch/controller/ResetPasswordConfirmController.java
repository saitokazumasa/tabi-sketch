package com.tabisketch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/password-reset/send")
public class ResetPasswordConfirmController {
    @GetMapping
    public String get(@ModelAttribute("currentMailAddress") final String currentMailAddress, final Model model) {
        model.addAttribute("currentMailAddress", currentMailAddress);
        return "user/password-reset/send";
    }
}