package com.tabisketch.bean.form;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteDayForm {
    @Min(1)
    private int dayId;
}
