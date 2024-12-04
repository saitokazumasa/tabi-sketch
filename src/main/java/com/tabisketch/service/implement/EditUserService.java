package com.tabisketch.service.implement;

import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IUsersMapper;
import org.springframework.stereotype.Service;

@Service
public class EditUserService {
    private final IUsersMapper usersMapper;

    public EditUserService(final IUsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    public User getUserByMail(final String mail) {
        return usersMapper.selectByMail(mail);
    }
    
    public boolean updateUserMailVerified(final int id, final boolean isMailVerified) {
        int rowsUpdated = usersMapper.updateMailVerified(id, isMailVerified);
        return rowsUpdated > 0;
    }
}
