package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.Destination;
import com.tabisketch.exception.FailedInsertException;
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
    public Destination execute(final Destination destination) throws FailedInsertException {
        final int result = this.destinationsMapper.insert(destination);
        if (result != 1) throw new FailedInsertException("Failed insert destinations.");
        return destination;
    }
}
