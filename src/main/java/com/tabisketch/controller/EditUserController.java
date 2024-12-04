package com.tabisketch.controller;

import com.tabisketch.bean.entity.User;
import com.tabisketch.service.implement.EditUserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/edit")
public class EditUserController {

    private final EditUserService editUserService;

    public EditUserController(final EditUserService editUserService) {
        this.editUserService = editUserService;
    }

    @GetMapping
    public String get(
            final @AuthenticationPrincipal UserDetails userDetails,
            final Model model
    ) {
        String email = userDetails.getUsername();
        User user = editUserService.getUserByMail(email);
        model.addAttribute("item", user);
        return "user/edit/index";
    }
}
