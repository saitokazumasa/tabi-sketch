package com.tabisketch.bean.form;

public class ExampleEditMailAddressForm {
    public static EditMailAddressForm generate() {
        return new EditMailAddressForm(
                "sample@example.com",
                "sample2@example.com",
                "password"
        );
    }
}
