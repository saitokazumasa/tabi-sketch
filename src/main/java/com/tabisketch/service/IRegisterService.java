package com.tabisketch.service;

import com.tabisketch.bean.form.RegisterForm;

public interface IRegisterService {
    /** @return 成功したか */
    boolean execute(final RegisterForm registerForm);
}
