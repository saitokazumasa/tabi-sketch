package com.tabisketch.controller;

import com.tabisketch.service.IDeletePlanService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shara/{uuid}/delete")
public class DeletePlanController {
    private final IDeletePlanService deletePlanService;

    public DeletePlanController(final IDeletePlanService deletePlanService) {
        this.deletePlanService = deletePlanService;
    }

    @PostMapping
    public String post(final @PathVariable String uuid) {
        // NOTE: プラン削除が完了したかどうかを画面に表示する場合はリターン値を使う
        this.deletePlanService.execute(uuid);
        return "redirect:/plan/list";
    }
}
