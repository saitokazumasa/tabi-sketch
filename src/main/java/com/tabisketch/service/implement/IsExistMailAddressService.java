package com.tabisketch.service.implement;

import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IIsExistMailAddressService;
import org.springframework.stereotype.Service;

@Service
public class IsExistMailAddressService implements IIsExistMailAddressService {
    private final IUsersMapper usersMapper;

    public IsExistMailAddressService(final IUsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public boolean execute(final String mailAddress) {
        return this.usersMapper.selectByMailAddress(mailAddress) != null;
    }
}
