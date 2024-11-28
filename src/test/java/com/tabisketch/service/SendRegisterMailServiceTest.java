package com.tabisketch.service;

import com.tabisketch.bean.form.RegisterForm;
import jakarta.mail.MessagingException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class SendRegisterMailServiceTest {
    @Autowired
    private ISendRegisterMailService sendRegisterMailService;

    @ParameterizedTest
    @MethodSource("動作するかのテストデータ")
    public void 動作するか(final RegisterForm registerForm) {
        // NOTE: アドレスエラーは検出されない
        try {
            sendRegisterMailService.execute(registerForm);
            assert true;
        } catch (final MessagingException e) {
            assert false;
        }
    }

    private static Stream<RegisterForm> 動作するかのテストデータ() {
        final var r = new RegisterForm("", "", "");
        return Stream.of(r);
    }
}
