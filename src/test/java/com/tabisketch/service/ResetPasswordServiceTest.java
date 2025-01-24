package com.tabisketch.service;

import com.tabisketch.bean.entity.ExamplePasswordResetToken;
import com.tabisketch.bean.entity.PasswordResetToken;
import com.tabisketch.bean.form.ExampleResetPasswordForm;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.SQLDataException;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
        final var resetPasswordForm = ExampleResetPasswordForm.generate();
        final var passwordResetToken = ExamplePasswordResetToken.generate();
        final var token = passwordResetToken.getToken().toString();


        when(this.passwordResetTokensMapper.selectByToken(any())).thenReturn(passwordResetToken);
        when(this.usersMapper.updatePassword(anyInt(), any())).thenReturn(1);
        when(this.passwordResetTokensMapper.deleteByUserId(anyInt())).thenReturn(1);

        this.resetPasswordService.execute(token, resetPasswordForm.getPassword());

        verify(this.usersMapper).updatePassword(anyInt(), any());
        verify(this.passwordResetTokensMapper).selectByToken(any());
        verify(this.passwordResetTokensMapper).deleteByUserId(anyInt());
    }
}
