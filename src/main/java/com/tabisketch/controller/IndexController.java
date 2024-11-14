package com.tabisketch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
public class IndexController {
    @GetMapping("")
    public String index() {
        return "index";
    }
    
    @GetMapping("/top")
    public String top() {
        return "index";
    }
}
