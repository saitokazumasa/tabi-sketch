package com.tabisketch.controller;

import com.tabisketch.bean.form.ResetPasswordForm;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.service.IResetPasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLDataException;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequestMapping("/password-reset/{token}")
public class ResetPasswordController {
    private final IResetPasswordService resetPasswordService;

    public ResetPasswordController(
            final IResetPasswordService resetPasswordService
    ) {
        this.resetPasswordService = resetPasswordService;
    }

    @GetMapping
    public String get(final @PathVariable String token, final Model model) {
        final var resetPasswordForm = ResetPasswordForm.empty();
        resetPasswordForm.setToken(token);
        model.addAttribute("resetPasswordForm", resetPasswordForm);
        return "password-reset/index";
    }

    @PostMapping
    public String post(
            final @Validated ResetPasswordForm resetPasswordForm,
            final BindingResult bindingResult
    ) throws DeleteFailedException, UpdateFailedException, SQLDataException {
        if (bindingResult.hasErrors()) return "password-reset/index";

        resetPasswordService.execute(token, resetPasswordForm.getPassword());

        return "redirect:/login";
    }
}
