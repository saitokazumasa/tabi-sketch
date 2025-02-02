package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.StartPlace;
import com.tabisketch.exception.FailedInsertException;
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
    public StartPlace execute(final StartPlace startPlace) throws FailedInsertException {
        final int result = this.startPlacesMapper.insert(startPlace);
        if (result != 1) throw new FailedInsertException("Failed insert start_places.");
        return startPlace;
    }
}
