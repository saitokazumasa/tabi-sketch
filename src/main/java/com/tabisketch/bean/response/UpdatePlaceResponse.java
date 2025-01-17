package com.tabisketch.bean.response;

import lombok.Getter;

@Getter
public class UpdatePlaceResponse implements IResponse {
    private final String status;

    private UpdatePlaceResponse(final String status) {
        this.status = status;
    }

    public static UpdatePlaceResponse success() {
        return new UpdatePlaceResponse(Status.Success.toString());
    }

    public static UpdatePlaceResponse failed() {
        return new UpdatePlaceResponse(Status.Failed.toString());
    }
}
