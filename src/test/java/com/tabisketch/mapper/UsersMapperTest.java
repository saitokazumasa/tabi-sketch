package com.tabisketch.mapper;

import com.tabisketch.bean.entity.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.stream.Stream;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersMapperTest {
    @Autowired
    private IUsersMapper usersMapper;

    @ParameterizedTest
    @ValueSource(strings = {"sample@example.com"})
    public void SELECTできるか(final String mail) {
        final User user = usersMapper.selectByMail(mail);
        assert user != null;
    }

    @ParameterizedTest
    @MethodSource("provideUsers")
    public void INSERTできるか(final User user) {
        final int result = usersMapper.insert(user);
        assert result == 1;
        assert user.id != -1;
    }

    public static Stream<User> provideUsers() {
        final var u1 = new User(-1, "sample2@example.com", "password", false);
        return Stream.of(u1);
    }
}
