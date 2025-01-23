package com.tabisketch.service;

import com.tabisketch.bean.entity.PasswordResetToken;
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
        final String token = "67da4e4f-a427-4a02-b920-a0d399b75217";
        final UUID tokenUUID = UUID.fromString(token);
        final String password = "password";
        final PasswordResetToken passwordResetToken = new PasswordResetToken(
                1,
                tokenUUID,
                1,
                null
        );

        when(this.passwordResetTokensMapper.selectByToken(any())).thenReturn(passwordResetToken);
        when(this.usersMapper.updatePassword(anyInt(), any())).thenReturn(1);
        when(this.passwordResetTokensMapper.deleteByUserId(anyInt())).thenReturn(1);

        this.resetPasswordService.execute(token, password);

        verify(this.usersMapper).updatePassword(anyInt(), any());
        verify(this.passwordResetTokensMapper).selectByToken(any());
        verify(this.passwordResetTokensMapper).deleteByUserId(anyInt());
    }
}
