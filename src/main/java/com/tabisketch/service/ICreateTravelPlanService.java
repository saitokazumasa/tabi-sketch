package com.tabisketch.service;

import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;

public interface ICreateTravelPlanService {
    TravelPlan execute(final TravelPlan entity) throws FailedInsertException, FailedSelectException;
}
