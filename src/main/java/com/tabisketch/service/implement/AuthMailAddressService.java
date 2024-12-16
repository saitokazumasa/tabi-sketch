package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.MAAToken;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IAuthMailAddressService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthMailAddressService implements IAuthMailAddressService {
    private final IMAATokensMapper maaTokensMapper;
    private final IUsersMapper usersMapper;

    public AuthMailAddressService(
            final IMAATokensMapper maaTokensMapper,
            final IUsersMapper usersMapper
    ) {
        this.maaTokensMapper = maaTokensMapper;
        this.usersMapper = usersMapper;
    }

    @Override
    @Transactional
    public boolean execute(final String token) {
        final var tokenUUID = UUID.fromString(token);
        final var mailAddressAuthToken = this.maaTokensMapper.selectByToken(tokenUUID);

        if (mailAddressAuthToken == null) return false;

        this.usersMapper.updateMailAddressVerified(mailAddressAuthToken.getUserId(), true);

        // メールアドレス編集の認証時はメールアドレスを更新
        if (isNotEmptyNewMailAddress(mailAddressAuthToken))
            this.usersMapper.updateMailAddress(mailAddressAuthToken.getUserId(), mailAddressAuthToken.getNewMailAddress());

        this.maaTokensMapper.deleteById(mailAddressAuthToken.getId());
        return true;
    }

    private boolean isNotEmptyNewMailAddress(final MAAToken maaToken) {
        return maaToken.getNewMailAddress() != null &&
                !maaToken.getNewMailAddress().isEmpty();
    }
}
