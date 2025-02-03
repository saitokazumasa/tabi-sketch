package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

/** 旅行プラン */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TravelPlan {
    /** 識別子 */
    private int id;
    /** 識別子文字列 */
    private UUID uuid;
    /** タイトル */
    private String title;
    /** 編集可否フラグ */
    private boolean editable;
    /** 公開閲覧可否フラグ */
    private boolean publiclyViewable;
    /** 関連する「ユーザー」の識別子 */
    private int userId;
}
