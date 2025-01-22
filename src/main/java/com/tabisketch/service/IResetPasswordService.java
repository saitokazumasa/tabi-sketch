package com.tabisketch.service;

import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.UpdateFailedException;

import java.sql.SQLDataException;
import java.util.UUID;


public interface IResetPasswordService {
    void execute(final UUID token, final String mailAddress) throws UpdateFailedException, DeleteFailedException, SQLDataException;
}
