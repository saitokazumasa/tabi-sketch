package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/** 目的地 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Destination {
    /** 識別子 */
    private int id;
    /** GoogleMapのPlaceId */
    private String placeId;
    /** 順番 */
    private int order;
    /** ユーザーが指定した訪れる時間 */
    private LocalTime specifiedStartTime;
    /** 訪れる時間 */
    private LocalTime startTime;
    /** 滞在時間（分） */
    private int durationMinutes;
    /** 予算（円） */
    private int budget;
    /** 関連する「目的地リスト」の識別子 */
    private int destinationListId;
}
