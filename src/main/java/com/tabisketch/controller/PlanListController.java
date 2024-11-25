package com.tabisketch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("plan/list")
public class PlanListController {
    @GetMapping()
    public String planList() {
        return "plan/list";
    }

}
