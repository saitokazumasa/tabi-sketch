package com.tabisketch.service;

import com.tabisketch.valueobject.Mail;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.UUID;

@SpringBootTest
public class SendMailServiceTest {
    @MockBean
    private JavaMailSender __; // DIで使用している
    @Autowired
    private ISendMailService sendMailService;

    // NOTE: アドレスエラーは検出されない
    @Test
    @WithMockUser
    public void testExist() throws MessagingException {
        final String mailAddress = "sample@example.com";

        final var registrationMail = Mail.registrationMail(mailAddress, UUID.randomUUID());
        this.sendMailService.execute(registrationMail);

        final var mailAddressEditMail = Mail.mailAddressEditMail(mailAddress, UUID.randomUUID());
        this.sendMailService.execute(mailAddressEditMail);
    }
}
