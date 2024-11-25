package com.tabisketch.bean.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserForm{
    @Email
    @NotBlank
    private String mail;

    @NotBlank
    private String password;

    @NotBlank
    private String rePassword;

    public static CreateUserForm empty() {
        return new CreateUserForm();
    }

    public boolean isNotMatchPasswordAndRePassword() {
        if (password == null || rePassword == null) return false;
        if (password.isEmpty() || rePassword.isEmpty()) return false;

        return !password.equals(rePassword);
    }
}
