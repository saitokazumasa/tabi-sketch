package com.tabisketch.service;

import com.tabisketch.bean.form.CreateDayForm;
import com.tabisketch.exception.InsertFailedException;

public interface ICreateDayService {
    int execute(final CreateDayForm createDayForm) throws InsertFailedException;
}
