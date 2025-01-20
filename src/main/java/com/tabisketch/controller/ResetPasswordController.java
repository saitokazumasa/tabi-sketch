package com.tabisketch.controller;

import com.tabisketch.bean.entity.PasswordResetToken;
import com.tabisketch.bean.form.ResetPasswordForm;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.service.IResetPasswordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/password-reset/{token}")
public class ResetPasswordController {
    private final IPasswordResetTokensMapper passwordResetTokensMapper;
    private final IResetPasswordService resetPasswordService;

    public ResetPasswordController(
            final IPasswordResetTokensMapper passwordResetTokensMapper,
            final IResetPasswordService resetPasswordService
    ) {
        this.passwordResetTokensMapper = passwordResetTokensMapper;
        this.resetPasswordService = resetPasswordService;
    }

    @GetMapping
    public String get(
            @PathVariable final String token,
            final Model model
    ) {
        final UUID tokenUUID = UUID.fromString(token);
        System.out.println(passwordResetTokensMapper.selectByToken(tokenUUID));

        if (passwordResetTokensMapper.selectByToken(tokenUUID) == null) {
            return "redirect:/login";
        }

        model.addAttribute("token", token);
        model.addAttribute("resetPasswordForm", ResetPasswordForm.empty());

        return "password-reset/index";
    }

    @PostMapping
    public String post(
            @PathVariable final String token,
            final ResetPasswordForm resetPasswordForm
    ) {
        final UUID tokenUUID = UUID.fromString(token);
        final PasswordResetToken passwordResetToken = passwordResetTokensMapper.selectByToken(tokenUUID);

        if (passwordResetToken == null) {
            return "redirect:/login";
        }

        if (!resetPasswordForm.getPassword().equals(resetPasswordForm.getRePassword())) {
            return "redirect:/login";
        }

        // DBに新しいパスワードをセットする処理
        System.out.println(token);

        resetPasswordService.execute(passwordResetToken.getUserId(), resetPasswordForm.getPassword());

        System.out.println(resetPasswordForm.getPassword());
        System.out.println(resetPasswordForm.getRePassword());

        return "redirect:/login";
    }
}
