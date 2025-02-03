package com.tabisketch.bean.request;

public class ExampleCreateDestinationListAPIRequest {
    private ExampleCreateDestinationListAPIRequest() {}

    public static CreateDestinationListAPIRequest generate() {
        return new CreateDestinationListAPIRequest(
                1,
                "1111",
                99
        );
    }
}
