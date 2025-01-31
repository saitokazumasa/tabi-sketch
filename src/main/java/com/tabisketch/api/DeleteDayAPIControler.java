package com.tabisketch.api;

import com.tabisketch.bean.form.DeleteDayForm;
import com.tabisketch.bean.response.implement.DeleteDayResponse;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.service.IDeleteDayService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delete-day")
public class DeleteDayAPIControler {
    private final IDeleteDayService deleteDayService;

    public DeleteDayAPIControler(final IDeleteDayService deleteDayService) {
        this.deleteDayService = deleteDayService;
    }

    @PostMapping
    public DeleteDayResponse post(
            final @Validated DeleteDayForm deleteDayForm,
            final BindingResult bindingResult
    ) throws DeleteFailedException {
        if (bindingResult.hasErrors()) return DeleteDayResponse.failed();

        this.deleteDayService.execute(deleteDayForm);
        return DeleteDayResponse.success();
    }
}
