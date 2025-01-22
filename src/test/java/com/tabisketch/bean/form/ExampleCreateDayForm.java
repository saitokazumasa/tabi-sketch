package com.tabisketch.bean.form;

public class ExampleCreateDayForm {
    public static CreateDayForm generate() {
        return new CreateDayForm(
                1,
                1,
                0,
                "0000",
                true,
                true
        );
    }
}
