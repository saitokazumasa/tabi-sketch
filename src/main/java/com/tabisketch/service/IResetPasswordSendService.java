package com.tabisketch.service;

import com.tabisketch.bean.form.ResetPasswordSendForm;
import com.tabisketch.exception.InsertFailedException;
import jakarta.mail.MessagingException;

public interface IResetPasswordSendService {
    void execute(final ResetPasswordSendForm resetPasswordSendForm) throws InsertFailedException, MessagingException;
}
