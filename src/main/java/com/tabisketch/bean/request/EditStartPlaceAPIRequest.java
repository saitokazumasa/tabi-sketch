package com.tabisketch.bean.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EditStartPlaceAPIRequest {
    /** 識別子 */
    @Min(1)
    private int id;
    /** GoogleMapのPlaceId */
    @NotBlank
    private String placeId;
    /** 出発時間 */
    @NotNull
    private LocalDateTime departureDateTime;
}
