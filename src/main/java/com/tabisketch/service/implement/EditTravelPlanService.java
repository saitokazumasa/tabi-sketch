package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.TravelPlan;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.exception.FailedUpdateException;
import com.tabisketch.mapper.ITravelPlansMapper;
import com.tabisketch.service.IEditTravelPlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditTravelPlanService implements IEditTravelPlanService {
    private final ITravelPlansMapper travelPlansMapper;

    public EditTravelPlanService(final ITravelPlansMapper travelPlansMapper) {
        this.travelPlansMapper = travelPlansMapper;
    }

    @Override
    @Transactional
    public TravelPlan execute(final TravelPlan entity) throws FailedUpdateException, FailedSelectException {
        final int result = this.travelPlansMapper.update(entity);
        if (result != 1) throw new FailedUpdateException("Failed update " + entity.getClass().getName());

        final var updatedEntity = this.travelPlansMapper.selectById(entity.getId());
        if (updatedEntity == null) throw new FailedSelectException("Failed select " + entity.getClass().getName());
        return updatedEntity;
    }
}
