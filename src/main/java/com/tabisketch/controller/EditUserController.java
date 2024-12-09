package com.tabisketch.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/edit")
public class EditUserController {
    @GetMapping
    public String get(@AuthenticationPrincipal(expression = "username") String mail, Model model) {
        model.addAttribute("mail", mail);
        return "user/edit/index";
    }
}
