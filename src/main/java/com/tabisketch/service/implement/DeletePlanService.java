package com.tabisketch.service.implement;

import com.tabisketch.mapper.IPlansMapper;
import com.tabisketch.service.IDeletePlanService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeletePlanService implements IDeletePlanService {
    final IPlansMapper plansMapper;

    public  DeletePlanService(final IPlansMapper plansMapper) {
        this.plansMapper = plansMapper;
    }

    @Override
    @Transactional
    public boolean execute(final String uuid) {
        final var u = UUID.fromString(uuid);
        final var plan = this.plansMapper.selectByUUID(u);

        // TODO: Place DB から削除
        // TODO: Day DB から削除
        final int planResult = this.plansMapper.deleteById(plan.getId());

        return planResult == 1;
    }
}
