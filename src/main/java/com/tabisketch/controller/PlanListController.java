package com.tabisketch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plan/list")
public class PlanListController {
    @GetMapping()
    public String planList() {
        return "plan/list";
    }

}
