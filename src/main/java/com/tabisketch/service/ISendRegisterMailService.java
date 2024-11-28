package com.tabisketch.service;

import com.tabisketch.bean.form.RegisterForm;
import jakarta.mail.MessagingException;

public interface ISendRegisterMailService {
    void execute(final RegisterForm registerForm) throws MessagingException;
}
