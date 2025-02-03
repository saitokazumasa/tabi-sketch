package com.tabisketch.controller;

import com.tabisketch.bean.request.CreateTravelPlanAPIRequest;
import com.tabisketch.bean.response.CreateTravelPlanAPIResponse;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.exception.InvalidRequestBodyException;
import com.tabisketch.service.ICreateTravelPlanService;
import com.tabisketch.util.RequestClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            final @RequestBody @Validated CreateTravelPlanAPIRequest request,
            final BindingResult bindingResult
    ) throws InvalidRequestBodyException, FailedInsertException, FailedSelectException {
        if (bindingResult.hasErrors()) throw new InvalidRequestBodyException("Invalid request.:" + request);

        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var updatedEntity = this.createTravelPlanService.execute(entity);

        return new CreateTravelPlanAPIResponse(updatedEntity);
    }
}
