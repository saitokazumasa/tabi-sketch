package com.tabisketch.bean.entity;

import java.time.LocalDate;
import java.util.UUID;

public class ExampleTravelPlan {
    private ExampleTravelPlan() {}

    public static TravelPlan generate() {
        return new TravelPlan(
                99,
                UUID.fromString("611d4008-4c0d-4b45-bd1b-21c97e7df3b2"),
                "title",
                LocalDate.now(),
                true,
                false,
                99
        );
    }
}
