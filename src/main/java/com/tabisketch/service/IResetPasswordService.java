package com.tabisketch.service;

import java.util.UUID;

public interface IResetPasswordService {
    void execute(final int userId, final String mailAddress);
}
