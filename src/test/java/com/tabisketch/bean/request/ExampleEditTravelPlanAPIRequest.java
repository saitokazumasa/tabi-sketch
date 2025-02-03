package com.tabisketch.bean.request;

import java.time.LocalDate;

public class ExampleEditTravelPlanAPIRequest {
    private ExampleEditTravelPlanAPIRequest() {}

    public static EditTravelPlanAPIRequest generate() {
        return new EditTravelPlanAPIRequest(
                99,
                "edited",
                LocalDate.now(),
                false,
                true
        );
    }
}
