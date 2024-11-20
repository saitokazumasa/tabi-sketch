package com.tabisketch.controller;

import com.tabisketch.bean.form.CreateUserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/create")
public class CreateUserController {
    @GetMapping
    public String get(final Model model) {
        model.addAttribute("createUserForm", CreateUserForm.empty());
        return "user/create";
    }

    @PostMapping
    public String post(
            final @Validated CreateUserForm createUserForm,
            final BindingResult bindingResult
    ) {
        if (createUserForm.isNotMatchPasswordAndRePassword())
            // TODO: エラーメッセージ等、ベタ書きではなく別の場所から参照する形にする
            bindingResult.rejectValue("rePassword", "error.createUserForm", "パスワードが一致しません");

        if (bindingResult.hasErrors()) return "user/create";

        // TODO: 確認メールを送信する

        return "redirect:/user/create/confirm";
    }

    @GetMapping("/confirm")
    public String confirm() {
        return "user/create-confirm";
    }
}
