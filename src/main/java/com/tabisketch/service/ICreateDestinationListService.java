package com.tabisketch.service;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;

public interface ICreateDestinationListService {
    DestinationList execute(final DestinationList entity) throws FailedInsertException, FailedSelectException;
}
