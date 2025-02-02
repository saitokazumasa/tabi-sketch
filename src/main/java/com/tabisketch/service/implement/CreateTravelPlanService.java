package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.mapper.ITravelPlansMapper;
import com.tabisketch.service.ICreateTravelPlanService;
import org.springframework.stereotype.Service;

@Service
public class CreateTravelPlanService implements ICreateTravelPlanService {
    private final ITravelPlansMapper travelPlansMapper;

    public CreateTravelPlanService(final ITravelPlansMapper travelPlansMapper) {
        this.travelPlansMapper = travelPlansMapper;
    }

    @Override
    public TravelPlan execute(final TravelPlan travelPlan) throws FailedInsertException {
        final int result = this.travelPlansMapper.insert(travelPlan);
        if (result != 1) throw new FailedInsertException("Failed insert travel_plans.");
        return travelPlan;
    }
}
