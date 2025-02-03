package com.tabisketch.bean.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditDestinationListAPIRequest {
    /** 識別子 */
    @Min(1)
    private int id;
    /** 有効な移動手段の2進数リスト */
    @NotBlank
    @Size(min = 4, max = 4)
    private String availableTransportationListBinary;
}
