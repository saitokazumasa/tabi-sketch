package com.tabisketch.controller;

import com.tabisketch.bean.request.CreateStartPlaceAPIRequest;
import com.tabisketch.bean.response.CreateStartPlaceAPIResponse;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.InvailedRequestException;
import com.tabisketch.service.ICreateStartPlaceService;
import com.tabisketch.util.RequestClassUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/create/start-place")
public class CreateStartPlaceAPIController {
    private final ICreateStartPlaceService createStartPlaceService;

    public CreateStartPlaceAPIController(final ICreateStartPlaceService createStartPlaceService) {
        this.createStartPlaceService = createStartPlaceService;
    }

    @PostMapping
    public CreateStartPlaceAPIResponse post(
            final @RequestBody @Validated CreateStartPlaceAPIRequest request,
            final BindingResult bindingResult
    ) throws InvailedRequestException, FailedInsertException {
        if (bindingResult.hasErrors()) throw new InvailedRequestException("Invalid request.:" + request);

        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var updatedEntity = this.createStartPlaceService.execute(entity);

        return new CreateStartPlaceAPIResponse(updatedEntity);
    }
}
