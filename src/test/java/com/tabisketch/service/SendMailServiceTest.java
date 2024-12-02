package com.tabisketch.service;

import com.tabisketch.bean.valueobject.Mail;
import jakarta.mail.MessagingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class SendMailServiceTest {
    @Autowired
    private ISendMailService sendMailService;

    @ParameterizedTest
    @MethodSource("動作するかのテストデータ")
    public void 動作するか(final Mail mail) throws MessagingException {
        // NOTE: アドレスエラーは検出されない
        sendMailService.execute(mail);
    }

    private static Stream<Mail> 動作するかのテストデータ() {
        final var m = new Mail("", "", "");
        return Stream.of(m);
    }
}
