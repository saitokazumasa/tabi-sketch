package com.tabisketch.service;

import com.tabisketch.bean.entity.Destination;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;

public interface ICreateDestinationService {
    Destination execute(final Destination entity) throws FailedInsertException, FailedSelectException;
}
