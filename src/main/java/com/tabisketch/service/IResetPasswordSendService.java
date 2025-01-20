package com.tabisketch.service;

import com.tabisketch.exception.InsertFailedException;
import jakarta.mail.MessagingException;

public interface IResetPasswordSendService {
    void execute(final String mailAddress) throws InsertFailedException, MessagingException;
}
