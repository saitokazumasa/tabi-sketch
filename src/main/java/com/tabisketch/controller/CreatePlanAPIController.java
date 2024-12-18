package com.tabisketch.controller;

import com.tabisketch.bean.Result.CreatePlanResult;
import com.tabisketch.bean.form.CreatePlanForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.service.ICreatePlanService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plan/create")
public class CreatePlanAPIController {
    private final ICreatePlanService createPlanService;

    public CreatePlanAPIController(final ICreatePlanService createPlanService) {
        this.createPlanService = createPlanService;
    }

    @PostMapping
    public CreatePlanResult post(
            final @Validated CreatePlanForm createPlanForm,
            final BindingResult bindingResult
    ) throws InsertFailedException {
        if (bindingResult.hasErrors()) return CreatePlanResult.failed();

        this.createPlanService.execute(createPlanForm);
        return CreatePlanResult.success();
    }
}
