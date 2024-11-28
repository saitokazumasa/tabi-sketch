package com.tabisketch.service;

import jakarta.mail.MessagingException;

public interface ISendRegisterMailService {
    void execute(final String toMailAddress) throws MessagingException;
}
