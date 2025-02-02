package com.tabisketch.bean.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/** 目的地作成リクエスト */
@Data
@AllArgsConstructor
public class CreateDestinationAPIRequest {
    /** GoogleMapのPlaceId */
    @NotBlank
    private String placeId;
    /** ユーザーが指定した訪れる時間 */
    private LocalTime specifiedStartTime;
    /** 滞在時間（分） */
    @Min(0)
    private int durationMinutes;
    /** 予算（円） */
    @Min(0)
    private int budget;
    /** 関連する「目的地リスト」の識別子 */
    @Min(1)
    private int destinationListId;
}
