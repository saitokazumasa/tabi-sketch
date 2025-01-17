package com.tabisketch.bean.response;

import lombok.Getter;

@Getter
public class CreatePlaceResponse implements IResponse {
    private final String status;

    private CreatePlaceResponse(final String status) {
        this.status = status;
    }

    public static CreatePlaceResponse success() {
        return new CreatePlaceResponse(Status.Success.toString());
    }

    public static CreatePlaceResponse failed() {
        return new CreatePlaceResponse(Status.Failed.toString());
    }
}
