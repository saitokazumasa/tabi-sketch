package com.tabisketch.util;

import com.tabisketch.bean.entity.Destination;
import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.bean.entity.StartPlace;
import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.bean.request.CreateDestinationListAPIRequest;
import com.tabisketch.bean.request.CreateDestinationAPIRequest;
import com.tabisketch.bean.request.CreateStartPlaceAPIRequest;
import com.tabisketch.bean.request.CreateTravelPlanAPIRequest;

public class RequestClassUtils {
    private RequestClassUtils() {}

    public static TravelPlan parseToEntityClass(final CreateTravelPlanAPIRequest createTravelPlanAPIRequest) {
        return new TravelPlan(
                -1,
                null,
                createTravelPlanAPIRequest.getTitle(),
                createTravelPlanAPIRequest.getActionDate(),
                true,
                false,
                createTravelPlanAPIRequest.getUserId()
        );
    }

    public static DestinationList parseToEntityClass(final CreateDestinationListAPIRequest createDestinationListAPIRequest) {
        return new DestinationList(
                -1,
                createDestinationListAPIRequest.getTravelDay(),
                createDestinationListAPIRequest.getAvailableTransportationListBinary(),
                createDestinationListAPIRequest.getTravelPlanId()
        );
    }

    public static Destination parseToEntityClass(final CreateDestinationAPIRequest createDestinationAPIRequest) {
        return new Destination(
                -1,
                createDestinationAPIRequest.getPlaceId(),
                -1,
                createDestinationAPIRequest.getSpecifiedStartTime(),
                null,
                createDestinationAPIRequest.getDurationMinutes(),
                createDestinationAPIRequest.getBudget(),
                createDestinationAPIRequest.getDestinationListId()
        );
    }

    public static StartPlace parseToEntityClass(final CreateStartPlaceAPIRequest createStartPlaceAPIRequest) {
        return new StartPlace(
                -1,
                createStartPlaceAPIRequest.getPlaceId(),
                createStartPlaceAPIRequest.getDepartureTime(),
                createStartPlaceAPIRequest.getDestinationListId()
        );
    }
}
