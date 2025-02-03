package com.tabisketch.util;

import com.tabisketch.bean.entity.Destination;
import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.bean.entity.StartPlace;
import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.bean.request.*;

public class RequestClassUtils {
    private RequestClassUtils() {}

    public static TravelPlan parseToEntityClass(final CreateTravelPlanAPIRequest request) {
        return new TravelPlan(
                -1,
                null,
                "",
                request.getActionDate(),
                true,
                false,
                request.getUserId()
        );
    }

    public static DestinationList parseToEntityClass(final CreateDestinationListAPIRequest request) {
        return new DestinationList(
                -1,
                request.getTravelDay(),
                request.getAvailableTransportationListBinary(),
                request.getTravelPlanId()
        );
    }

    public static Destination parseToEntityClass(final CreateDestinationAPIRequest request) {
        return new Destination(
                -1,
                request.getPlaceId(),
                -1,
                request.getSpecifiedStartTime(),
                null,
                request.getDurationMinutes(),
                request.getBudget(),
                request.getDestinationListId()
        );
    }

    public static StartPlace parseToEntityClass(final CreateStartPlaceAPIRequest request) {
        return new StartPlace(
                -1,
                request.getPlaceId(),
                request.getDepartureTime(),
                request.getDestinationListId()
        );
    }

    public static TravelPlan parseToEntityClass(final EditTravelPlanAPIRequest request) {
        return new TravelPlan(
                request.getId(),
                null,
                request.getTitle(),
                request.getActionDate(),
                request.isEditable(),
                request.isPubliclyViewable(),
                -1
        );
    }

    public static DestinationList parseToEntityClass(final EditDestinationListAPIRequest request) {
        return new DestinationList(
                request.getId(),
                -1,
                request.getAvailableTransportationListBinary(),
                -1
        );
    }
}
