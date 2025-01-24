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
    public void testSelect() {
        final int id = ExampleUser.generate().getId();
        assert this.usersMapper.selectById(id) != null;

        final String mailAddress = ExampleUser.generate().getMailAddress();
        assert this.usersMapper.selectByMailAddress(mailAddress) != null;
    }

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testUpdate() {
        final var user = ExampleUser.generate();
        user.setMailAddressAuthenticated(true);
        assert this.usersMapper.update(user) == 1;
    }
}
