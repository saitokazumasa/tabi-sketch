package com.tabisketch.service;

import com.tabisketch.bean.form.RegisterForm;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.implement.RegisterService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

@SpringBootTest
public class RegisterServiceTest {
    @MockBean
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
}
