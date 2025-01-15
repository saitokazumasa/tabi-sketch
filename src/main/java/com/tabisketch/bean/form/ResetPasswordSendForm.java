package com.tabisketch.bean.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResetPasswordSendForm {
    @Email
    @NotBlank
    private String currentMailAddress;

    public static ResetPasswordSendForm empty() {
        return new ResetPasswordSendForm("");
    }
}
