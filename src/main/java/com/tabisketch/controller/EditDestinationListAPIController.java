package com.tabisketch.controller;

import com.tabisketch.bean.request.EditDestinationListAPIRequest;
import com.tabisketch.bean.response.EditDestinationListAPIResponse;
import com.tabisketch.exception.FailedUpdateException;
import com.tabisketch.exception.InvalidRequestBodyException;
import com.tabisketch.service.IEditDestinationListService;
import com.tabisketch.util.RequestClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/edit/destination-list")
public class EditDestinationListAPIController {
    private final IEditDestinationListService editDestinationListService;

    public EditDestinationListAPIController(final IEditDestinationListService editDestinationListService) {
        this.editDestinationListService = editDestinationListService;
    }

    @PostMapping
    public EditDestinationListAPIResponse post(
            final @RequestBody @Validated EditDestinationListAPIRequest request,
            final BindingResult bindingResult
    ) throws InvalidRequestBodyException, FailedUpdateException {
        if (bindingResult.hasErrors()) throw new InvalidRequestBodyException("Invalid request.:" + request);

        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var updatedEntity = this.editDestinationListService.execute(entity);

        return new EditDestinationListAPIResponse(updatedEntity);
    }
}
