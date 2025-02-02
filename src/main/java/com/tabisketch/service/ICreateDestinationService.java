package com.tabisketch.service;

import com.tabisketch.bean.entity.Destination;
import com.tabisketch.exception.FailedInsertException;

public interface ICreateDestinationService {
    Destination execute(final Destination destination) throws FailedInsertException;
}
