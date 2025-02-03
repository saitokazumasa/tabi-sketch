package com.tabisketch.bean.request;

public class ExampleEditTravelPlanAPIRequest {
    private ExampleEditTravelPlanAPIRequest() {}

    public static EditTravelPlanAPIRequest generate() {
        return new EditTravelPlanAPIRequest(
                99,
                "edited",
                false,
                true
        );
    }
}
