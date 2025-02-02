package com.tabisketch.bean.request;

public class ExampleCreateDestinationAPIRequest {
    private ExampleCreateDestinationAPIRequest() {}

    public static CreateDestinationAPIRequest generate() {
        return new CreateDestinationAPIRequest(
                "placeId",
                null,
                0,
                0,
                1
        );
    }
}
