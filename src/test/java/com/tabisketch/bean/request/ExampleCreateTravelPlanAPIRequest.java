package com.tabisketch.bean.request;

import java.time.LocalDate;

public class ExampleCreateTravelPlanAPIRequest {
    private ExampleCreateTravelPlanAPIRequest() {}

    public static CreateTravelPlanAPIRequest generate() {
        return new CreateTravelPlanAPIRequest(
                "title",
                LocalDate.now(),
                99
        );
    }
}
