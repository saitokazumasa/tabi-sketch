package com.tabisketch.service;

import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.UpdateFailedException;


public interface IResetPasswordService {
    void execute(final int userId, final String mailAddress) throws UpdateFailedException, DeleteFailedException;
}
