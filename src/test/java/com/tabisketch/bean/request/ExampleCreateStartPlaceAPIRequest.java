package com.tabisketch.bean.request;

import java.time.LocalDateTime;

public class ExampleCreateStartPlaceAPIRequest {
    private ExampleCreateStartPlaceAPIRequest() {}

    public static CreateStartPlaceAPIRequest generate() {
        return new CreateStartPlaceAPIRequest(
                "placeId",
                LocalDateTime.now(),
                99
        );
    }
}
