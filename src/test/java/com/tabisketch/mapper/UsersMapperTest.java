package com.tabisketch.mapper;

import com.tabisketch.bean.User;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersMapperTest {
    @Autowired
    private IUsersMapper usersMapper;

    @ParameterizedTest
    // NOTE: 存在するテストデータに合わせて以下を書き換える
    @ValueSource(strings = {"sample@mail.com"})
    public void SELECTできるか(final String mail) {
        final User user = usersMapper.selectByMail(mail);
        assert user != null;
    }
}
