package com.tabisketch.service;

import jakarta.mail.MessagingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SendRegisterMailServiceTest {
    @Autowired
    private ISendRegisterMailService sendRegisterMailService;

    @ParameterizedTest
    @ValueSource(strings = {""})
    public void 動作するか(final String toMail) {
        // NOTE: アドレスエラーは検出されない
        try {
            sendRegisterMailService.execute(toMail);
            assert true;
        } catch (final MessagingException e) {
            assert false;
        }
    }
}
