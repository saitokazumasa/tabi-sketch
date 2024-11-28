package com.tabisketch.service.implement;

import com.tabisketch.bean.form.RegisterForm;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.IRegisterService;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterService {
    private final IUsersMapper usersMapper;

    public RegisterService(final IUsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    public boolean execute(final RegisterForm registerForm) {
        try {
            final int result = this.usersMapper.insert(registerForm.toUser());
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
