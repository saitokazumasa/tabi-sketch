package com.tabisketch.controller;

import com.tabisketch.bean.request.CreateTravelPlanAPIRequest;
import com.tabisketch.bean.response.CreateTravelPlanAPIResponse;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.InvailedRequestException;
import com.tabisketch.service.ICreateTravelPlanService;
import com.tabisketch.util.EntityClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create/travel-plan")
public class CreateTravelPlanAPIController {
    private final ICreateTravelPlanService createTravelPlanService;

    public CreateTravelPlanAPIController(final ICreateTravelPlanService createTravelPlanService) {
        this.createTravelPlanService = createTravelPlanService;
    }

    @PostMapping
    public CreateTravelPlanAPIResponse post(
            final @Validated CreateTravelPlanAPIRequest request,
            final BindingResult bindingResult
    ) throws InvailedRequestException, FailedInsertException {
        if (bindingResult.hasErrors()) throw new InvailedRequestException("Invalid request.");

        final var entity = EntityClassUtils.parseToTravelPlan(request);
        final var updatedEntity = this.createTravelPlanService.execute(entity);

        return new CreateTravelPlanAPIResponse(updatedEntity);
    }
}
