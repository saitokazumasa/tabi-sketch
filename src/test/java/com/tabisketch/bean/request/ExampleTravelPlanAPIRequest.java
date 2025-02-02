package com.tabisketch.bean.request;

import java.time.LocalDate;

public class ExampleTravelPlanAPIRequest {
    private ExampleTravelPlanAPIRequest() {}

    public static CreateTravelPlanAPIRequest generate() {
        return new CreateTravelPlanAPIRequest(
                "title",
                LocalDate.now(),
                1
        );
    }
}
