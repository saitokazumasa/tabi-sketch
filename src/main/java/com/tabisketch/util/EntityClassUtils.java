package com.tabisketch.util;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.bean.request.CreateDestinationListAPIRequest;
import com.tabisketch.bean.request.CreateTravelPlanAPIRequest;

public class EntityClassUtils {
    private EntityClassUtils() {}

    public static TravelPlan parseToTravelPlan(final CreateTravelPlanAPIRequest createTravelPlanAPIRequest) {
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

    public static DestinationList parseToDestinationList(final CreateDestinationListAPIRequest createDestinationListAPIRequest) {
        return new DestinationList(
                -1,
                createDestinationListAPIRequest.getTravelDay(),
                createDestinationListAPIRequest.getAvailableTransportationListBinary(),
                createDestinationListAPIRequest.getTravelPlanId()
        );
    }
}
