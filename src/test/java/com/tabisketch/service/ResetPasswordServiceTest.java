package com.tabisketch.service;

import com.tabisketch.bean.entity.ExamplePasswordResetToken;
import com.tabisketch.bean.entity.ExampleUser;
import com.tabisketch.bean.form.ExampleResetPasswordForm;
import com.tabisketch.exception.DeleteFailedException;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.sql.SQLDataException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ResetPasswordServiceTest {
    @MockitoBean
    private IPasswordResetTokensMapper passwordResetTokensMapper;
    @MockitoBean
    private IUsersMapper usersMapper;
    @MockitoBean
    private ISendMailService sendMailService;
    @Autowired
    private IResetPasswordService resetPasswordService;

    @Test
    public void testExecute() throws UpdateFailedException, DeleteFailedException, SQLDataException, MessagingException, SelectFailedException {
        final var passwordResetToken = ExamplePasswordResetToken.generate();
        final var user = ExampleUser.generate();

        when(this.passwordResetTokensMapper.selectByToken(any())).thenReturn(passwordResetToken);
        when(this.usersMapper.selectById(anyInt())).thenReturn(user);
        when(this.usersMapper.updatePassword(anyInt(), any())).thenReturn(1);
        when(this.passwordResetTokensMapper.deleteById(anyInt())).thenReturn(1);

        final var resetPasswordForm = ExampleResetPasswordForm.generate();
        this.resetPasswordService.execute(resetPasswordForm);

        verify(this.passwordResetTokensMapper).selectByToken(any());
        verify(this.usersMapper).selectById(anyInt());
        verify(this.usersMapper).updatePassword(anyInt(), any());
        verify(this.passwordResetTokensMapper).deleteById(anyInt());
        verify(this.sendMailService).execute(any());
    }
}
