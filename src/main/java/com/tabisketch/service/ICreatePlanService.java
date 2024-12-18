package com.tabisketch.service;

import com.tabisketch.bean.form.CreatePlanForm;
import com.tabisketch.exception.InsertFailedException;

public interface ICreatePlanService {
    void execute(final CreatePlanForm createPlanForm) throws InsertFailedException;
}
