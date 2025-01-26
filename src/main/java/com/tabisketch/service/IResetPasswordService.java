package com.tabisketch.service;

import com.tabisketch.bean.form.ResetPasswordForm;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.exception.UpdateFailedException;

public interface IResetPasswordService {
    void execute(final ResetPasswordForm resetPasswordForm) throws SelectFailedException, UpdateFailedException, DeleteFailedException;
}
