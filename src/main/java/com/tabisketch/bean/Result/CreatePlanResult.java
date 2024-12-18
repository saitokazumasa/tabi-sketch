package com.tabisketch.bean.Result;

import lombok.Getter;

@Getter
public class CreatePlanResult {
    private enum Status {
        SUCCESS,
        FAILED
    }

    private final String status;

    private CreatePlanResult(final String status) {
        this.status = status;
    }

    public static CreatePlanResult success() {
        return new CreatePlanResult(Status.SUCCESS.toString());
    }

    public static CreatePlanResult failed() {
        return new CreatePlanResult(Status.FAILED.toString());
    }
}
