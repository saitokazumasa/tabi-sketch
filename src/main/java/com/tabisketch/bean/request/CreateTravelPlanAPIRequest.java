package com.tabisketch.bean.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreateTravelPlanAPIRequest {
    /** 日付 */
    @NotNull
    private LocalDate actionDate;
    /** 関連する「ユーザー」の識別子 */
    @Min(1)
    private int userId;
}
