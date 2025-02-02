//package com.tabisketch.bean.form;
//
//import com.tabisketch.util.StringUtils;
//import jakarta.validation.constraints.AssertTrue;
//import jakarta.validation.constraints.NotBlank;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class ResetPasswordForm {
//    @NotBlank
//    private String token;
//
//    @NotBlank
//    private String password;
//
//    @NotBlank
//    private String rePassword;
//
//    public static ResetPasswordForm empty() {
//        return new ResetPasswordForm("", "", "");
//    }
//
//    @AssertTrue(message = "パスワードが一致しません")
//    public boolean isMatchPasswordAndRePassword() {
//        if (StringUtils.nullAndEmpty(this.password)) return false;
//        if (StringUtils.nullAndEmpty(this.rePassword)) return false;
//
//        return password.equals(rePassword);
//    }
//}
