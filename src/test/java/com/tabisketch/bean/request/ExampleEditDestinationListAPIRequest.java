package com.tabisketch.bean.request;

public class ExampleEditDestinationListAPIRequest {
    private ExampleEditDestinationListAPIRequest() {}

    public static EditDestinationListAPIRequest generate() {
        return new EditDestinationListAPIRequest(
                99,
                "1111"
        );
    }
}
