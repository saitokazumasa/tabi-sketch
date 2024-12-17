package com.tabisketch.service;

import com.tabisketch.bean.form.EditMailAddressForm;
import jakarta.mail.MessagingException;

public interface IEditMailAddressService {
    void execute(final EditMailAddressForm editMailAddressForm) throws MessagingException;
}
