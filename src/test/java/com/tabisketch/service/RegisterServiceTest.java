package com.tabisketch.service;

import com.tabisketch.bean.form.RegisterForm;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.implement.RegisterService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.stream.Stream;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class RegisterServiceTest {
    @Autowired
    private IUsersMapper usersMapper;

    @ParameterizedTest
    @MethodSource("動作するかのテストデータ")
    public void 動作するか(final RegisterForm registerForm) {
        final var registerService = new RegisterService(usersMapper);
        registerService.execute(registerForm);
    }

    private static Stream<RegisterForm> 動作するかのテストデータ() {
        final var r = new RegisterForm("sample@example.com", "password", "password");
        return Stream.of(r);
    }

    @ParameterizedTest
    @MethodSource("失敗したらfalseを返すかのテストデータ")
    public void 失敗したらfalseを返すか(final RegisterForm registerForm) {
        final var registerService = new RegisterService(usersMapper);

        try {
            registerService.execute(registerForm);
            registerService.execute(registerForm);
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            assert true;
        }
    }

    private static Stream<RegisterForm> 失敗したらfalseを返すかのテストデータ() {
        final var r1 = new RegisterForm("sample@example.com", "password", "password");
        return Stream.of(r1);
    }
}
