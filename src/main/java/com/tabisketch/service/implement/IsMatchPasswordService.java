package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IIsMatchPasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class IsMatchPasswordService implements IIsMatchPasswordService {
    final IUsersMapper usersMapper;
    final PasswordEncoder passwordEncoder;

    public IsMatchPasswordService(final IUsersMapper usersMapper, final PasswordEncoder passwordEncoder) {
        this.usersMapper = usersMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean execute(final String mailAddress, final String password) {
        final User user = this.usersMapper.selectByMailAddress(mailAddress);
        return passwordEncoder.matches(password, user.getPassword());
    }
}
