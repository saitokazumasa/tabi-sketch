package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;
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
    public TravelPlan execute(final TravelPlan entity) throws FailedInsertException, FailedSelectException {
        final int result = this.travelPlansMapper.insert(entity);
        if (result != 1) throw new FailedInsertException("Failed insert " + entity.getClass().getName());

        final var updatedEntity = this.travelPlansMapper.selectById(entity.getId());
        if (updatedEntity == null) throw new FailedSelectException("Failed select " + entity.getClass().getName());
        return updatedEntity;
    }
}
