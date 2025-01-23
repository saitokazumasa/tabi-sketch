package com.tabisketch.service;

import com.tabisketch.bean.entity.PasswordResetToken;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.SQLDataException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ResetPasswordServiceTest {
    @MockitoBean
    private IUsersMapper usersMapper;
    @MockitoBean
    private IPasswordResetTokensMapper passwordResetTokensMapper;
    @MockitoBean
    private ISendMailService sendMailService;
    @Autowired
    private IResetPasswordService resetPasswordService;

    @Test
    public void testExecute() throws UpdateFailedException, DeleteFailedException, SQLDataException {
        when(this.passwordResetTokensMapper.selectByToken(any())).thenReturn(new PasswordResetToken());
        when(this.usersMapper.updatePassword(any(), any())).thenReturn(1);
        when(this.passwordResetTokensMapper.deleteByUserId(any())).thenReturn(1);

        final String token = "67da4e4f-a427-4a02-b920-a0d399b75217";
        final String password = "password";

        this.resetPasswordService.execute(token, password);

        verify(this.usersMapper).updatePassword(any(), any());
        verify(this.passwordResetTokensMapper).selectByToken(any());
        verify(this.passwordResetTokensMapper).deleteByUserId(any());
    }
}
