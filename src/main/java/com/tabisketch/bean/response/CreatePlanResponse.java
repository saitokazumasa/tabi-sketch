package com.tabisketch.bean.response;

import lombok.Getter;

@Getter
public class CreatePlanResponse {
    private enum Status {
        SUCCESS,
        FAILED
    }

    private final String status;

    private CreatePlanResponse(final String status) {
        this.status = status;
    }

    public static CreatePlanResponse success() {
        return new CreatePlanResponse(Status.SUCCESS.toString());
    }

    public static CreatePlanResponse failed() {
        return new CreatePlanResponse(Status.FAILED.toString());
    }
}
