package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 目的地までの移動情報 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteInfo {
    /** 識別子 */
    private int id;
    /** 目的地へ向かうまでの交通手段 */
    private String travelMode;
    /** 目的地へ向かうまでの移動時間（分） */
    private int travelTimeMinutes;
    /** 関連する「目的地」の識別子 */
    private int destinationId;
}
