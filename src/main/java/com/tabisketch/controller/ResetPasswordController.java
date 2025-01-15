package com.tabisketch.controller;

import com.tabisketch.bean.form.ResetPasswordSendForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/password-reset")
public class ResetPasswordController {
    @GetMapping
    public String get(final Model model) {
        model.addAttribute("resetPasswordSendForm", ResetPasswordSendForm.empty());
        return "password-reset/send";
    }
}
