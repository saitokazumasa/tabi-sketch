package com.tabisketch.bean.form;

public class ExampleResetPasswordForm {
    public static ResetPasswordForm generate() {
        return new ResetPasswordForm(
                "password",
                "password"
        );
    }
}
