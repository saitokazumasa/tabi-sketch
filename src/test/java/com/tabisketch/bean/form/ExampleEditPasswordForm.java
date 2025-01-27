package com.tabisketch.bean.form;

public class ExampleEditPasswordForm {
    public static EditPasswordForm generate() {
        return new EditPasswordForm(
                "sample@example.com",
                "password",
                "password2",
                "password2"
        );
    }
}
