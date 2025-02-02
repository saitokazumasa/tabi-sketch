package com.tabisketch.service;

import com.tabisketch.bean.entity.StartPlace;
import com.tabisketch.exception.FailedInsertException;

public interface ICreateStartPlaceService {
    StartPlace execute(final StartPlace startPlace) throws FailedInsertException;
}
