package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.exception.FailedInsertException;
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
    public DestinationList execute(DestinationList destinationList) throws FailedInsertException {
        final int result = this.destinationListsMapper.insert(destinationList);
        if (result != 1) throw new FailedInsertException("Failed insert destination_lists.");
        return destinationList;
    }
}
