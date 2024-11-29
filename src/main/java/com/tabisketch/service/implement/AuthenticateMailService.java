package com.tabisketch.service.implement;

import com.tabisketch.mapper.IMailAuthenticationTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IAuthenticateMailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthenticateMailService implements IAuthenticateMailService {
    private final IMailAuthenticationTokensMapper mailAuthenticationTokensMapper;
    private final IUsersMapper usersMapper;

    public AuthenticateMailService(
            final IMailAuthenticationTokensMapper mailAuthenticationTokensMapper,
            final IUsersMapper usersMapper
    ) {
        this.mailAuthenticationTokensMapper = mailAuthenticationTokensMapper;
        this.usersMapper = usersMapper;
    }

    @Override
    @Transactional
    public boolean execute(final String token) {
        final var tokenUUID = UUID.fromString(token);
        final var mailAuth = this.mailAuthenticationTokensMapper.selectByToken(tokenUUID);

        if (mailAuth == null) return false;

        this.usersMapper.updateMailVerified(mailAuth.getUserId(), true);
        this.mailAuthenticationTokensMapper.deleteById(mailAuth.getId());
        return true;
    }
}
