package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 持ち物 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Belonging {
    /** 識別子 */
    private int id;
    /** 項目名 */
    private String label;
    /** 関連する「旅行プラン」の識別子 */
    private int travelPlanId;
}
