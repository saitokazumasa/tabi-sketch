package com.tabisketch.controller;

import com.tabisketch.bean.request.CreateDestinationAPIRequest;
import com.tabisketch.bean.response.CreateDestinationAPIResponse;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.InvailedRequestException;
import com.tabisketch.service.ICreateDestinationService;
import com.tabisketch.util.RequestClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create/destination")
public class CreateDestinationAPIController {
    private final ICreateDestinationService createDestinationService;

    public CreateDestinationAPIController(final ICreateDestinationService createDestinationService) {
        this.createDestinationService = createDestinationService;
    }

    @PostMapping
    public CreateDestinationAPIResponse post(
            final @RequestBody @Validated CreateDestinationAPIRequest request,
            final BindingResult bindingResult
    ) throws InvailedRequestException, FailedInsertException {
        if (bindingResult.hasErrors()) throw new InvailedRequestException("Invalid request.:" + request);

        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var updatedEntity = this.createDestinationService.execute(entity);

        return new CreateDestinationAPIResponse(updatedEntity);
    }
}
