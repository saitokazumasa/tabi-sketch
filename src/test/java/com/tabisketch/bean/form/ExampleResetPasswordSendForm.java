package com.tabisketch.bean.form;

public class ExampleResetPasswordSendForm {
    public static ResetPasswordSendForm generate() {
        return new ResetPasswordSendForm(
                "sample@example.com"
        );
    }
}
