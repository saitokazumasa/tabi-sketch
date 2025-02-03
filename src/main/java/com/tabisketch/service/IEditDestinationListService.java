package com.tabisketch.service;

import com.tabisketch.bean.entity.DestinationList;
import com.tabisketch.exception.FailedSelectException;
import com.tabisketch.exception.FailedUpdateException;

public interface IEditDestinationListService {
    DestinationList execute(final DestinationList entity) throws FailedUpdateException, FailedSelectException;
}
