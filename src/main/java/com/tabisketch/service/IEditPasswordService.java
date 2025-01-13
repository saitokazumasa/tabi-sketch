package com.tabisketch.service;

import com.tabisketch.bean.form.EditPasswordForm;
import com.tabisketch.exception.UpdateFailedException;
import jakarta.mail.MessagingException;

public interface IEditPasswordService {
    void execute(final EditPasswordForm editPasswordForm) throws UpdateFailedException, MessagingException;
}
