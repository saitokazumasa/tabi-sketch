package com.tabisketch.bean.response.implement;

import com.tabisketch.bean.response.IResponse;
import lombok.Getter;

@Getter
public class DeleteDayResponse implements IResponse {
    private final String status;

    private DeleteDayResponse(final String status) {
        this.status = status;
    }

    public static DeleteDayResponse success() {
        return new DeleteDayResponse(Status.Success.toString());
    }

    public static DeleteDayResponse failed() {
        return new DeleteDayResponse(Status.Failed.toString());
    }
}
