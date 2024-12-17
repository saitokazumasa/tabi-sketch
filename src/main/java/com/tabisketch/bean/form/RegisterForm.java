package com.tabisketch.bean.form;

import com.tabisketch.bean.entity.User;
import com.tabisketch.util.StringUtils;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterForm {
    @Email
    @NotBlank
    private String mailAddress;

    @NotBlank
    private String password;

    @NotBlank
    private String rePassword;

    public static RegisterForm empty() {
        return new RegisterForm("", "", "");
    }

    public boolean isNotMatchPasswordAndRePassword() {
        if (StringUtils.isNullAndEmpty(this.password)) return false;
        if (StringUtils.isNullAndEmpty(this.rePassword)) return false;

        return !password.equals(rePassword);
    }
}
