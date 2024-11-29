package com.tabisketch.service;

import com.tabisketch.bean.entity.MailAuthenticationToken;
import com.tabisketch.mapper.IMailAuthenticationTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.implement.AuthenticateMailService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthenticateMailServiceTest {
    @MockBean
    private IMailAuthenticationTokensMapper mailAuthenticationTokensMapper;
    @MockBean
    private IUsersMapper usersMapper;

    @ParameterizedTest
    @ValueSource(strings = {"a2e69add-9d95-4cf1-a59b-cedbb95dcd6b"})
    public void 動作するか(final String token) {
        final var tokenUUID = UUID.fromString(token);
        when(this.mailAuthenticationTokensMapper.selectByToken(tokenUUID)).thenReturn(new MailAuthenticationToken());

        final var authenticateMailService =
                new AuthenticateMailService(this.mailAuthenticationTokensMapper, this.usersMapper);
        assert authenticateMailService.execute(token);
    }
}
