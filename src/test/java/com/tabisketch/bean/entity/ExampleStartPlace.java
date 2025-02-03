package com.tabisketch.bean.entity;

import java.time.LocalTime;

public class ExampleStartPlace {
    private ExampleStartPlace() {}

    public static StartPlace generate() {
        return new StartPlace(
                99,
                "placeId",
                LocalTime.now(),
                99
        );
    }
}
