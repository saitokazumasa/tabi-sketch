package com.tabisketch.service.implement;

import com.tabisketch.mapper.IDaysMapper;
import com.tabisketch.mapper.IPlacesMapper;
import com.tabisketch.mapper.IPlansMapper;
import com.tabisketch.service.IDeletePlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeletePlanService implements IDeletePlanService {
    private final IPlansMapper plansMapper;
    private final IDaysMapper daysMapper;
    private final IPlacesMapper placesMapper;

    public  DeletePlanService(
            final IPlansMapper plansMapper,
            final IDaysMapper daysMapper,
            final IPlacesMapper placesMapper
    ) {
        this.plansMapper = plansMapper;
        this.daysMapper = daysMapper;
        this.placesMapper = placesMapper;
    }

    @Override
    @Transactional
    public void execute(final String planUUID) {
        final var uuid = UUID.fromString(planUUID);
        this.placesMapper.deleteByPlanUUID(uuid);
        this.daysMapper.deleteByPlanUUID(uuid);
        this.plansMapper.deleteByUUID(uuid);
    }
}
