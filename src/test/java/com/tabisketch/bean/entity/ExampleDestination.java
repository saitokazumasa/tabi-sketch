package com.tabisketch.bean.entity;

public class ExampleDestination {
    private ExampleDestination() {}

    public static Destination generate() {
        return new Destination(
                99,
                "placeId",
                -1,
                null,
                null,
                0,
                0,
                99
        );
    }
}
