package com.tabisketch.mapper;

import com.tabisketch.bean.entity.User;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersMapperTest {
    @Autowired
    private IUsersMapper usersMapper;

    @Test
    public void testInsert() {
        final var user = User.generate(
                "sample@example.com",
                "$2a$10$FFbAunp0hfeWTCune.XqwO/P/61fqWlbruV/8wqzrhM3Pw0VuXxpa"
        );
        this.usersMapper.insert(user);
        assert user.getId() != -1;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testSelect() {
        final int id = 1;
        assert this.usersMapper.selectById(id) != null;

        final String mailAddress = "sample@example.com";
        assert this.usersMapper.selectByMailAddress(mailAddress) != null;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testUpdate() {
        final var user = User.generate("", "");
        this.usersMapper.update(user);
    }
}
