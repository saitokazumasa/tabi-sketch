package com.tabisketch.service.implement;

import com.tabisketch.mapper.IMailAuthenticationTokensMapper;
import com.tabisketch.service.IAuthenticateMailService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthenticateMailService implements IAuthenticateMailService {
    private final IMailAuthenticationTokensMapper mailAuthenticationTokensMapper;

    public AuthenticateMailService(final IMailAuthenticationTokensMapper mailAuthenticationTokensMapper) {
        this.mailAuthenticationTokensMapper = mailAuthenticationTokensMapper;
    }

    @Override
    public boolean execute(final String token) {
        final var tokenUUID = UUID.fromString(token);
        final var mailAuth = this.mailAuthenticationTokensMapper.selectByToken(tokenUUID);

        if (mailAuth == null) return false;

        // TODO: メール認証完了

        return true;
    }
}
