package com.tabisketch.mapper;

import com.tabisketch.bean.entity.ExamplePasswordResetToken;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PasswordResetTokensMapperTest {
    @Autowired
    private IPasswordResetTokensMapper passwordResetTokensMapper;

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testInsert() {
        final var passwordResetToken = ExamplePasswordResetToken.generate();
        assert this.passwordResetTokensMapper.insert(passwordResetToken) == 1;
        assert passwordResetToken.getId() != -1;
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePasswordResetToken.sql"
    })
    public void testSelect() {
        final var tokenUUID = ExamplePasswordResetToken.generate().getToken();
        final var token = passwordResetTokensMapper.selectByToken(tokenUUID);
        assert token != null;
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePasswordResetToken.sql"
    })
    public void testDelete() {
        final int id = ExamplePasswordResetToken.generate().getId();
        assert this.passwordResetTokensMapper.deleteById(id) == 1;
    }
}
