package com.tabisketch.service;

import com.tabisketch.bean.entity.ExampleUser;
import com.tabisketch.bean.form.ExampleSendResetPasswordForm;
import com.tabisketch.exception.InsertFailedException;
import com.tabisketch.exception.SelectFailedException;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.mapper.IPasswordResetTokensMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SendResetPasswordServiceTest {
    @MockitoBean
    private IUsersMapper usersMapper;
    @MockitoBean
    private IPasswordResetTokensMapper passwordResetTokensMapper;
    @MockitoBean
    private ISendMailService sendMailService;
    @Autowired
    private ISendResetPasswordService resetPasswordSendService;

    @Test
    public void testExecute() throws MessagingException, InsertFailedException, SelectFailedException {
        final var user = ExampleUser.generate();

        when(this.usersMapper.selectByMailAddress(any())).thenReturn(user);
        when(this.passwordResetTokensMapper.insert(any())).thenReturn(1);

        final var sendResetPasswordForm = ExampleSendResetPasswordForm.generate();
        this.resetPasswordSendService.execute(sendResetPasswordForm);

        verify(this.usersMapper).selectByMailAddress(any());
        verify(this.passwordResetTokensMapper).insert(any());
        verify(this.sendMailService).execute(any());
    }
}
