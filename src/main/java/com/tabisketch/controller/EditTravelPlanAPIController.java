package com.tabisketch.controller;

import com.tabisketch.bean.request.EditTravelPlanAPIRequest;
import com.tabisketch.bean.response.EditTravelPlanAPIResponse;
import com.tabisketch.exception.FailedUpdateException;
import com.tabisketch.exception.InvailedRequestException;
import com.tabisketch.service.IEditTravelPlanService;
import com.tabisketch.util.RequestClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/edit/travel-plan")
public class EditTravelPlanAPIController {
    private final IEditTravelPlanService editTravelPlanService;

    public EditTravelPlanAPIController(final IEditTravelPlanService editTravelPlanService) {
        this.editTravelPlanService = editTravelPlanService;
    }

    @PostMapping
    public EditTravelPlanAPIResponse post(
            final @RequestBody @Validated EditTravelPlanAPIRequest request,
            final BindingResult bindingResult
    ) throws InvailedRequestException, FailedUpdateException {
        if (bindingResult.hasErrors()) throw new InvailedRequestException("Invalid request.:" + request);

        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var updatedEntity = this.editTravelPlanService.execute(entity);

        return new EditTravelPlanAPIResponse(updatedEntity);
    }
}
