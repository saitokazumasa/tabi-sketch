package com.tabisketch.mapper;

import com.tabisketch.bean.entity.ExampleUser;
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
        final var user = ExampleUser.generate();
        assert this.usersMapper.insert(user) == 1;
        assert user.getId() != -1;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testSelectById() {
        final int id = ExampleUser.generate().getId();
        assert this.usersMapper.selectById(id) != null;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testSelectByMailAddress() {
        final String mailAddress = ExampleUser.generate().getMailAddress();
        assert this.usersMapper.selectByMailAddress(mailAddress) != null;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testUpdateMailAddress() {
        final var user = ExampleUser.generate();
        assert this.usersMapper.updateMailAddress(user.getId(), "new_" + user.getMailAddress()) == 1;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testUpdatePassword() {
        final var user = ExampleUser.generate();
        assert this.usersMapper.updatePassword(user.getId(), "new_" + user.getPassword()) == 1;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testUpdateMailAddressAuthenticated() {
        final var user = ExampleUser.generate();
        assert this.usersMapper.updateMailAddressAuthenticated(user.getId(), !user.getMailAddressAuthenticated()) == 1;
    }
}
