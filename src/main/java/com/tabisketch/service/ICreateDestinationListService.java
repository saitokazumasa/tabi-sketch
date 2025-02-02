package com.tabisketch.service;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.exception.FailedInsertException;

public interface ICreateDestinationListService {
    DestinationList execute(final DestinationList destinationList) throws FailedInsertException;
}
