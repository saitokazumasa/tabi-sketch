package com.tabisketch.mapper;

import com.tabisketch.bean.entity.MailAuthenticationToken;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.stream.Stream;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MailAuthenticationTokensMapperTest {
    @Autowired
    private IMailAuthenticationTokensMapper mailAuthenticationTokensMapper;

    @ParameterizedTest
    @MethodSource("INSERTできるかのテストデータ")
    public void INSERTできるか(final MailAuthenticationToken mailAuthenticationToken) {
        final int result = this.mailAuthenticationTokensMapper.insert(mailAuthenticationToken);
        assert result == 1;
        assert mailAuthenticationToken.getId() != -1;
    }

    private static Stream<MailAuthenticationToken> INSERTできるかのテストデータ() {
        final var m = MailAuthenticationToken.generate(1);
        return Stream.of(m);
    }

    @ParameterizedTest
    @ValueSource(strings = {"a8815804-ca73-436b-b1b0-f9c76ccaec90"})
    public void SELECTできるか(final String token) {
        final MailAuthenticationToken mailAuthenticationToken =
                this.mailAuthenticationTokensMapper.selectByToken(token);
        assert mailAuthenticationToken != null;
    }
}
