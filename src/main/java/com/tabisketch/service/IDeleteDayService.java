package com.tabisketch.service;

import com.tabisketch.bean.form.DeleteDayForm;
import com.tabisketch.exception.DeleteFailedException;

public interface IDeleteDayService {
    void execute(final DeleteDayForm deleteDayForm) throws DeleteFailedException;
}
