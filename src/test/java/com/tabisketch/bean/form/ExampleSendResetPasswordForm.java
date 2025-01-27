package com.tabisketch.bean.form;

public class ExampleSendResetPasswordForm {
    public static SendResetPasswordForm generate() {
        return new SendResetPasswordForm(
                "sample@example.com"
        );
    }
}
