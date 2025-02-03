package com.tabisketch.bean.request;

import java.time.LocalDate;

public class ExampleCreateTravelPlanAPIRequest {
    private ExampleCreateTravelPlanAPIRequest() {}

    public static CreateTravelPlanAPIRequest generate() {
        return new CreateTravelPlanAPIRequest(
                LocalDate.now(),
                99
        );
    }
}
