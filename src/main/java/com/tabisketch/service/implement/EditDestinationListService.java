package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.exception.FailedUpdateException;
import com.tabisketch.mapper.IDestinationListsMapper;
import com.tabisketch.service.IEditDestinationListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditDestinationListService implements IEditDestinationListService {
    private final IDestinationListsMapper destinationListsMapper;

    public EditDestinationListService(final IDestinationListsMapper destinationListsMapper) {
        this.destinationListsMapper = destinationListsMapper;
    }

    @Override
    @Transactional
    public DestinationList execute(final DestinationList entity) throws FailedUpdateException, FailedSelectException {
        final int result = this.destinationListsMapper.update(entity);
        if (result != 1) throw new FailedUpdateException("Failed update " + entity.getClass().getName());

        final var updatedEntity = this.destinationListsMapper.selectById(entity.getId());
        if (updatedEntity == null) throw new FailedSelectException("Failed select " + entity.getClass().getName());
        return updatedEntity;
    }
}
