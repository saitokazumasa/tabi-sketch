package com.tabisketch.bean.form;

import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.valueobject.EncryptedPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterForm {
    @Email
    @NotBlank
    private String mail;

    @NotBlank
    private String password;

    @NotBlank
    private String rePassword;

    public static RegisterForm empty() {
        return new RegisterForm();
    }

    public User toUser() {
        final String encryptedPassword = new EncryptedPassword(password).value();
        return User.generate(mail, encryptedPassword);
    }

    public boolean isNotMatchPasswordAndRePassword() {
        if (password == null || rePassword == null) return false;
        if (password.isEmpty() || rePassword.isEmpty()) return false;

        return !password.equals(rePassword);
    }
}
