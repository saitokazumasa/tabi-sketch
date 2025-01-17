package com.tabisketch.service;

import com.tabisketch.bean.form.UpdatePlaceForm;
import com.tabisketch.exception.UpdateFailedException;

public interface IUpdatePlaceService {
    void execute(final UpdatePlaceForm updatePlaceForm) throws UpdateFailedException;
}
