package com.tabisketch.service;

import com.tabisketch.bean.entity.StartPlace;
import com.tabisketch.exception.FailedInsertException;
import com.tabisketch.exception.FailedSelectException;

public interface ICreateStartPlaceService {
    StartPlace execute(final StartPlace entity) throws FailedInsertException, FailedSelectException;
}
