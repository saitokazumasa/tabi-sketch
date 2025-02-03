package com.tabisketch.bean.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateTravelPlanAPIRequest {
    /** 関連する「ユーザー」の識別子 */
    @Min(1)
    private int userId;
}
