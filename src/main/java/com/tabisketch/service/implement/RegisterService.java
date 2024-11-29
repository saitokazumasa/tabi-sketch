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
    public void execute(final RegisterForm registerForm) {
        this.usersMapper.insert(registerForm.toUser());
    }
}
