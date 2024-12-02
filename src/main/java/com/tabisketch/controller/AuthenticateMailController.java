package com.tabisketch.controller;

import com.tabisketch.service.IAuthenticateMailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail/confirm/{token}")
public class AuthenticateMailController {
    private final IAuthenticateMailService authenticateMailService;

    public AuthenticateMailController(final IAuthenticateMailService authenticateMailService) {
        this.authenticateMailService = authenticateMailService;
    }

    @GetMapping
    public String get(final @PathVariable String token) {
        // NOTE: メール認証が完了したかどうかを画面に表示する場合はリターン値を使う
        authenticateMailService.execute(token);
        return "mail/confirm";
    }
}
