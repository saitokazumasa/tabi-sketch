package com.tabisketch.bean.entity;

import java.time.LocalDateTime;

public class ExampleStartPlace {
    private ExampleStartPlace() {}

    public static StartPlace generate() {
        return new StartPlace(
                99,
                "placeId",
                LocalDateTime.now(),
                99
        );
    }
}
