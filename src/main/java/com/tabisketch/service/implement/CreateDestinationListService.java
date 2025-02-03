package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.mapper.IDestinationListsMapper;
import com.tabisketch.service.ICreateDestinationListService;
import org.springframework.stereotype.Service;

@Service
public class CreateDestinationListService implements ICreateDestinationListService {
    private final IDestinationListsMapper destinationListsMapper;

    public CreateDestinationListService(final IDestinationListsMapper destinationListsMapper) {
        this.destinationListsMapper = destinationListsMapper;
    }

    @Override
    public DestinationList execute(DestinationList entity) throws FailedInsertException, FailedSelectException {
        final int result = this.destinationListsMapper.insert(entity);
        if (result != 1) throw new FailedInsertException("Failed insert " + entity.getClass().getName());

        final var updatedEntity = this.destinationListsMapper.selectById(entity.getId());
        if (updatedEntity == null) throw new FailedSelectException("Failed select " + entity.getClass().getName());
        return updatedEntity;
    }
}
