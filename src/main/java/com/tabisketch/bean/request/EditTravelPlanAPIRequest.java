package com.tabisketch.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EditTravelPlanAPIRequest {
    /** 識別子 */
    private int id;
    /** タイトル */
    private String title;
    /** 日付 */
    private LocalDate actionDate;
    /** 編集可否フラグ */
    private boolean editable;
    /** 公開閲覧可否フラグ */
    private boolean publiclyViewable;
}
