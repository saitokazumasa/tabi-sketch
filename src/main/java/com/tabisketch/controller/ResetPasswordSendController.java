package com.tabisketch.controller;

import com.tabisketch.bean.form.ResetPasswordSendForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.service.implement.ResetPasswordSendService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/password-reset")
public class ResetPasswordSendController {
    private final ResetPasswordSendService sendPasswordResetService;

    public ResetPasswordSendController(ResetPasswordSendService sendPasswordResetService) {
        this.sendPasswordResetService = sendPasswordResetService;
    }

    @GetMapping
    public String get(final Model model) {
        model.addAttribute("resetPasswordSendForm", ResetPasswordSendForm.empty());
        return "password-reset/send";
    }

    @PostMapping
    public String post(
            final RedirectAttributes redirectAttributes,
            final ResetPasswordSendForm resetPasswordSendForm
    ) throws MessagingException, InsertFailedException {
        sendPasswordResetService.execute(resetPasswordSendForm.getCurrentMailAddress());

        final String currentMailAddress = resetPasswordSendForm.getCurrentMailAddress();
        redirectAttributes.addFlashAttribute("currentMailAddress", currentMailAddress);

        return "redirect:password-reset/send";
    }

    @GetMapping("/send")
    public String send(
            @ModelAttribute("currentMailAddress") final String currentMailAddress,
            final Model model
    ) {
        model.addAttribute("currentMailAddress", currentMailAddress);
        return "user/password-reset/send";
    }
}



