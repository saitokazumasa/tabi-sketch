package com.tabisketch.mapper;

import com.tabisketch.bean.entity.ExampleUser;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsersMapperTest {
    @Autowired
    private IUsersMapper mapper;

    @Test
    @Sql("classpath:/sql/InsertExampleUser.sql")
    public void testSelectByEmail() {
        final var entity = this.mapper.selectByEmail(ExampleUser.generate().getEmail());
        assert entity != null;
    }
}
