package com.tabisketch.bean.request;

import java.time.LocalTime;

public class ExampleStartPlaceAPIRequest {
    private ExampleStartPlaceAPIRequest() {}

    public static CreateStartPlaceAPIRequest generate() {
        return new CreateStartPlaceAPIRequest(
                "placeId",
                LocalTime.now(),
                99
        );
    }
}
