package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.StartPlace;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.mapper.IStartPlacesMapper;
import com.tabisketch.service.ICreateStartPlaceService;
import org.springframework.stereotype.Service;

@Service
public class CreateStartPlaceService implements ICreateStartPlaceService {
    final IStartPlacesMapper startPlacesMapper;

    public CreateStartPlaceService(final IStartPlacesMapper startPlacesMapper) {
        this.startPlacesMapper = startPlacesMapper;
    }

    @Override
    public StartPlace execute(final StartPlace entity) throws FailedInsertException, FailedSelectException {
        final int result = this.startPlacesMapper.insert(entity);
        if (result != 1) throw new FailedInsertException("Failed insert " + entity.getClass().getName());

        final var updatedEntity = this.startPlacesMapper.selectById(entity.getId());
        if (updatedEntity == null) throw new FailedSelectException("Failed select " + entity.getClass().getName());
        return updatedEntity;
    }
}
