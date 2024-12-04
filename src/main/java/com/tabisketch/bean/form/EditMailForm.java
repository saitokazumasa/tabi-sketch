package com.tabisketch.bean.form;

import com.tabisketch.bean.valueobject.EncryptedPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditMailForm {
    @Email
    @NotBlank
    private String newMail;

    @NotBlank
    private String currentPassword;

    public static EditMailForm empty() {
        return new EditMailForm();
    }

    public boolean isNotMatchPassword(final String encryptedPassword) {
        if (this.currentPassword == null || encryptedPassword == null) return false;
        if (this.currentPassword.isEmpty() || encryptedPassword.isEmpty()) return false;

        final var encryptedPasswordObj = new EncryptedPassword(encryptedPassword);
        return !encryptedPasswordObj.isMatch(this.currentPassword);
    }
}
