package com.tabisketch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mail/confirm")
public class AuthenticateMailController {
    public String get() {
        return "";
    }
}
