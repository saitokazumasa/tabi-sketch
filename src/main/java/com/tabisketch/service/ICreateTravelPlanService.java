package com.tabisketch.service;

import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.exception.FailedInsertException;

public interface ICreateTravelPlanService {
    TravelPlan execute(final TravelPlan travelPlan) throws FailedInsertException;
}
