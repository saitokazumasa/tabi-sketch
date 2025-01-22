package com.tabisketch.controller;

import com.tabisketch.bean.form.ResetPasswordSendForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.service.implement.ResetPasswordSendService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/password-reset")
public class ResetPasswordSendController {
    private final ResetPasswordSendService resetPasswordSendService;

    public ResetPasswordSendController(ResetPasswordSendService resetPasswordSendService) {
        this.resetPasswordSendService = resetPasswordSendService;
    }

    @GetMapping
    public String get(final Model model) {
        model.addAttribute("resetPasswordSendForm", ResetPasswordSendForm.empty());
        return "password-reset/send";
    }

    @PostMapping
    public String post(
            final RedirectAttributes redirectAttributes,
            final ResetPasswordSendForm resetPasswordSendForm,
            final BindingResult bindingResult
    ) throws MessagingException, InsertFailedException {
        if(bindingResult.hasErrors()) return "password-reset/send";

        final String currentMailAddress = resetPasswordSendForm.getCurrentMailAddress();

        resetPasswordSendService.execute(currentMailAddress);

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



