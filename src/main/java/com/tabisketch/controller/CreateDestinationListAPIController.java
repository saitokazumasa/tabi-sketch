package com.tabisketch.controller;

import com.tabisketch.bean.request.CreateDestinationListAPIRequest;
import com.tabisketch.bean.response.CreateDestinationListAPIResponse;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.InvailedRequestException;
import com.tabisketch.service.ICreateDestinationListService;
import com.tabisketch.util.RequestClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create/destination-list")
public class CreateDestinationListAPIController {
    private final ICreateDestinationListService createDestinationListService;

    public CreateDestinationListAPIController(final ICreateDestinationListService createDestinationListService) {
        this.createDestinationListService = createDestinationListService;
    }

    @PostMapping
    public CreateDestinationListAPIResponse post(
            final @RequestBody @Validated CreateDestinationListAPIRequest request,
            final BindingResult bindingResult
    ) throws InvailedRequestException, FailedInsertException {
        if (bindingResult.hasErrors()) throw new InvailedRequestException("Invalid request.:" + request);

        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var updatedEntity = this.createDestinationListService.execute(entity);

        return new CreateDestinationListAPIResponse(updatedEntity);
    }
}
