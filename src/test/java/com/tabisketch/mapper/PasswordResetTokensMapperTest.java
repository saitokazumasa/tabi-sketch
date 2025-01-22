package com.tabisketch.mapper;

import com.tabisketch.bean.entity.PasswordResetToken;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PasswordResetTokensMapperTest {
    @Autowired
    private IPasswordResetTokensMapper passwordResetTokensMapper;

    @Test
    @Sql("classpath:/sql/CreateUser.sql")
    public void testInsert() {
        final var passwordResetToken = PasswordResetToken.generate(1);
        assert this.passwordResetTokensMapper.insert(passwordResetToken) == 1;
        assert passwordResetToken.getId() != -1;
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePasswordResetToken.sql"
    })
    public void testSelect() {
        final UUID tokenUUID = UUID.fromString("d3a460f2-5df4-48b6-b9e1-a550e319512f");
        final var token = passwordResetTokensMapper.selectByToken(tokenUUID);
        assert token != null;
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePasswordResetToken.sql"
    })
    public void testDelete() {
        final int id = 1;
        assert this.passwordResetTokensMapper.deleteByUserId(id) == 1;
    }
}
