package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.exception.FailedUpdateException;
import com.tabisketch.mapper.ITravelPlansMapper;
import com.tabisketch.service.IEditTravelPlanService;
import org.springframework.stereotype.Service;

@Service
public class EditTravelPlanService implements IEditTravelPlanService {
    private final ITravelPlansMapper travelPlansMapper;

    public EditTravelPlanService(final ITravelPlansMapper travelPlansMapper) {
        this.travelPlansMapper = travelPlansMapper;
    }

    @Override
    public TravelPlan execute(final TravelPlan travelPlan) throws FailedUpdateException {
        final int result = this.travelPlansMapper.update(travelPlan);
        if (result != 1) throw new FailedUpdateException("Failed update travel_plans.");
        return travelPlan;
    }
}
