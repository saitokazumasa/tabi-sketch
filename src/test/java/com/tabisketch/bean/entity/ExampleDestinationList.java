package com.tabisketch.bean.entity;

public class ExampleDestinationList {
    private ExampleDestinationList() {}

    public static DestinationList generate() {
        return new DestinationList(
                99,
                1,
                "1111",
                99
        );
    }
}
