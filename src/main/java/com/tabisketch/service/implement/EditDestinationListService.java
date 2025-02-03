package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.exception.FailedUpdateException;
import com.tabisketch.mapper.IDestinationListsMapper;
import com.tabisketch.service.IEditDestinationListService;
import org.springframework.stereotype.Service;

@Service
public class EditDestinationListService implements IEditDestinationListService {
    private final IDestinationListsMapper destinationListsMapper;

    public EditDestinationListService(final IDestinationListsMapper destinationListsMapper) {
        this.destinationListsMapper = destinationListsMapper;
    }

    @Override
    public DestinationList execute(final DestinationList destinationList) throws FailedUpdateException {
        final int result = this.destinationListsMapper.update(destinationList);
        if (result != 1) throw new FailedUpdateException("Failed update destination_lists.");
        return destinationList;
    }
}
