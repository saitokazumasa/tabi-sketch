package com.tabisketch.service;

import com.tabisketch.bean.entity.ExampleUser;
import com.tabisketch.valueobject.Mail;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.UUID;

@SpringBootTest
public class SendMailServiceTest {
    @MockitoBean
    private JavaMailSender __; // DIで使用している
    @Autowired
    private ISendMailService sendMailService;

    // NOTE: アドレスエラーは検出されない

    @Test
    public void testExecuteRegistrationMail() throws MessagingException {
        final var mailAddress = ExampleUser.generate().getMailAddress();
        final var registrationMail = Mail.registrationMail(mailAddress, UUID.randomUUID());
        this.sendMailService.execute(registrationMail);
    }

    @Test
    public void testExecuteMailAddressEditMail() throws MessagingException {
        final var mailAddress = ExampleUser.generate().getMailAddress();
        final var mailAddressEditMail = Mail.mailAddressEditMail(mailAddress, UUID.randomUUID());
        this.sendMailService.execute(mailAddressEditMail);
    }

    @Test
    public void testExecutePasswordResetMail() throws MessagingException {
        final var mailAddress = ExampleUser.generate().getMailAddress();
        final var passwordResetMail = Mail.passwordResetMail(mailAddress, UUID.randomUUID());
        this.sendMailService.execute(passwordResetMail);
    }
}
