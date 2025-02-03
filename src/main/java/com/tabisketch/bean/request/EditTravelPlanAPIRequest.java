package com.tabisketch.bean.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EditTravelPlanAPIRequest {
    /** 識別子 */
    @Min(1)
    private int id;
    /** タイトル */
    @NotBlank
    private String title;
    /** 日付 */
    @NotNull
    private LocalDate actionDate;
    /** 編集可否フラグ */
    private boolean editable;
    /** 公開閲覧可否フラグ */
    private boolean publiclyViewable;
}
