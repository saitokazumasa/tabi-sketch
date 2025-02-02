package com.tabisketch.bean.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/** 旅行プラン作成APIのリクエスト */
@Data
@NoArgsConstructor
public class CreateTravelPlanAPIRequest {
    /** タイトル */
    @NotBlank
    private String title;
    /** 日付 */
    @NotNull
    private LocalDate actionDate;
    /** 関連する「ユーザー」の識別子 */
    @Min(1)
    private int userId;
}
