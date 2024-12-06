package com.tabisketch.controller;

import com.tabisketch.bean.form.SendEditMailForm;
import com.tabisketch.bean.form.IsMatchPasswordForm;
import com.tabisketch.service.IIsMatchPasswordService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user/edit/mail")
public class SendEditMailController {
    private final IIsMatchPasswordService isMatchPasswordService;

    public SendEditMailController(final IIsMatchPasswordService isMatchPasswordService) {
        this.isMatchPasswordService = isMatchPasswordService;
    }

    @GetMapping
    public String get(final Model model) {
        model.addAttribute("editMailForm", SendEditMailForm.empty());
        return "user/edit/mail/index";
    }

    @PostMapping
    public String post(
            final @Validated SendEditMailForm sendEditMailForm,
            final BindingResult bindingResult,
            final @AuthenticationPrincipal UserDetails userDetails,
            final RedirectAttributes redirectAttributes
    ) {

        if (isMatchPassword(userDetails.getPassword(), sendEditMailForm.getCurrentPassword()))
            // TODO: エラーメッセージ等、ベタ書きではなく別の場所から参照する形にする
            // パスワードが間違っていても表示せずに通して、処理だけ実行しない方がセキュリティ的には良いかも？
            bindingResult.rejectValue("currentPassword", "error.editMailForm", "パスワードが一致しません");
        if (bindingResult.hasErrors()) return "user/edit/mail/index";

        // TODO: 編集して確認メールを飛ばす

        redirectAttributes.addFlashAttribute("mail", sendEditMailForm.getNewMail());
        return "redirect:/user/edit/mail/send";
    }

    private boolean isMatchPassword(final String mail, final String password) {
        final var isMatchPasswordForm = new IsMatchPasswordForm(mail, password);
        return this.isMatchPasswordService.execute(isMatchPasswordForm);
    }

    @GetMapping("/send")
    public String send() {
        return "user/edit/mail/send";
    }
}
