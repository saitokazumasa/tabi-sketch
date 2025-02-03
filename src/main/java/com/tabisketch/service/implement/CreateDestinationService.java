package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.Destination;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.mapper.IDestinationsMapper;
import com.tabisketch.service.ICreateDestinationService;
import org.springframework.stereotype.Service;

@Service
public class CreateDestinationService implements ICreateDestinationService {
    private final IDestinationsMapper destinationsMapper;

    public CreateDestinationService(final IDestinationsMapper destinationsMapper) {
        this.destinationsMapper = destinationsMapper;
    }

    @Override
    public Destination execute(final Destination entity) throws FailedInsertException, FailedSelectException {
        final int result = this.destinationsMapper.insert(entity);
        if (result != 1) throw new FailedInsertException("Failed insert " + entity.getClass().getName());

        final var updatedEntity = this.destinationsMapper.selectById(entity.getId());
        if (updatedEntity == null) throw new FailedSelectException("Failed select " + entity.getClass().getName());
        return updatedEntity;
    }
}
