//package com.tabisketch.controller;
//
//import com.tabisketch.bean.form.ResetPasswordForm;
//import com.tabisketch.exception.DeleteFailedException;
//import com.tabisketch.exception.SelectFailedException;
//import com.tabisketch.exception.UpdateFailedException;
//import com.tabisketch.service.IResetPasswordService;
//import jakarta.mail.MessagingException;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.sql.SQLDataException;
//
//@Controller
//@RequestMapping("/password-reset/reset/{token}")
//public class ResetPasswordController {
//    private final IResetPasswordService resetPasswordService;
//
//    public ResetPasswordController(
//            final IResetPasswordService resetPasswordService
//    ) {
//        this.resetPasswordService = resetPasswordService;
//    }
//
//    @GetMapping
//    public String get(final @PathVariable String token, final Model model) {
//        final var resetPasswordForm = ResetPasswordForm.empty();
//        resetPasswordForm.setToken(token);
//        model.addAttribute("resetPasswordForm", resetPasswordForm);
//        return "password-reset/reset";
//    }
//
//    @PostMapping
//    public String post(
//            final @Validated ResetPasswordForm resetPasswordForm,
//            final BindingResult bindingResult
//    ) throws DeleteFailedException, UpdateFailedException, SQLDataException, SelectFailedException, MessagingException {
//        if (bindingResult.hasErrors()) return "password-reset/reset";
//
//        this.resetPasswordService.execute(resetPasswordForm);
//
//        return "redirect:/login";
//    }
//}
