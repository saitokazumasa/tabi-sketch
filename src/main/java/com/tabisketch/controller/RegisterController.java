package com.tabisketch.controller;

import com.tabisketch.bean.form.RegisterForm;
import com.tabisketch.service.IRegisterService;
import com.tabisketch.service.ISendRegisterMailService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final IRegisterService registerService;
    private final ISendRegisterMailService sendRegisterMailService;

    public RegisterController(
            final IRegisterService registerService,
            final ISendRegisterMailService sendRegisterMailService
    ) {
        this.registerService = registerService;
        this.sendRegisterMailService = sendRegisterMailService;
    }

    @GetMapping
    public String get(final Model model) {
        model.addAttribute("registerForm", RegisterForm.empty());
        return "register/index";
    }

    @PostMapping
    public String post(final @Validated RegisterForm registerForm, final BindingResult bindingResult) throws MessagingException {
        if (registerForm.isNotMatchPasswordAndRePassword())
            // TODO: エラーメッセージ等、ベタ書きではなく別の場所から参照する形にする
            bindingResult.rejectValue("rePassword", "error.createUserForm", "パスワードが一致しません");

        if (bindingResult.hasErrors()) return "register/index";

        this.registerService.execute(registerForm);
        // TODO: 送信エラーが発生した時どうするか考える
        this.sendRegisterMailService.execute(registerForm);

        return "redirect:/register/send";
    }

    @GetMapping("/send")
    public String send() {
        return "register/send";
    }
}
