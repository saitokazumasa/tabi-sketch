package com.tabisketch.service.implement;

import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IExistMailAddressService;
import org.springframework.stereotype.Service;

@Service
public class ExistMailAddressService implements IExistMailAddressService {
    private final IUsersMapper usersMapper;

    public ExistMailAddressService(final IUsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public boolean execute(final String mailAddress) {
        return this.usersMapper.selectByMailAddress(mailAddress) != null;
    }
}
