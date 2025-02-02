//package com.tabisketch.bean.form;
//
//import com.tabisketch.util.StringUtils;
//import jakarta.validation.constraints.AssertTrue;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class EditPasswordForm {
//    @Email
//    @NotBlank
//    private String mailAddress;
//
//    @NotBlank
//    private String currentPassword;
//
//    @NotBlank
//    private String newPassword;
//
//    @NotBlank
//    private String newRePassword;
//
//    public static EditPasswordForm empty() {
//        return new EditPasswordForm("", "", "", "");
//    }
//
//    @AssertTrue(message = "パスワードが一致しません")
//    public boolean isMatchPasswordAndRePassword() {
//        if (StringUtils.nullAndEmpty(this.newPassword)) return false;
//        if (StringUtils.nullAndEmpty(this.newRePassword)) return false;
//
//        return newPassword.equals(newRePassword);
//    }
//}
