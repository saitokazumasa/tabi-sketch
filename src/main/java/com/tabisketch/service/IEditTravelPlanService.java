package com.tabisketch.service;

import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.exception.FailedUpdateException;

public interface IEditTravelPlanService {
    TravelPlan execute(final TravelPlan travelPlan) throws FailedUpdateException;
}
