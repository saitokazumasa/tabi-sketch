package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.MAAToken;
import com.tabisketch.bean.entity.User;
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
    public boolean execute(final String maaTokenStr) {
        final var token = UUID.fromString(maaTokenStr);

        final MAAToken maaToken = this.maaTokensMapper.selectByToken(token);
        final User user = this.usersMapper.selectById(maaToken.getUserId());

        if (maaToken.getNewMailAddress() != null && !maaToken.getNewMailAddress().isEmpty()) {
            final var newUser = new User(
                    user.getId(),
                    maaToken.getNewMailAddress(),
                    user.getPassword(),
                    true
            );
            this.usersMapper.update(newUser);
            return true;
        }

        final var newUser = new User(
                user.getId(),
                user.getMailAddress(),
                user.getPassword(),
                true
        );
        this.usersMapper.update(newUser);
//        this.maaTokensMapper.deleteById(mailAddressAuthToken.getId());
        return true;
    }
}
