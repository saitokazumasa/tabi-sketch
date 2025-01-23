package com.tabisketch.service;

import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.ResetPasswordSendForm;
import com.tabisketch.exception.InsertFailedException;
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
public class ResetPasswordSendServiceTest {
    @MockitoBean
    private IUsersMapper usersMapper;
    @MockitoBean
    private IPasswordResetTokensMapper passwordResetTokensMapper;
    @MockitoBean
    private ISendMailService sendMailService;
    @Autowired
    private IResetPasswordSendService resetPasswordSendService;

    @Test
    public void testExecute() throws MessagingException, InsertFailedException {
        final ResetPasswordSendForm resetPasswordSendForm = new ResetPasswordSendForm(
                "sample@example.com"
        );
        final User user = new User(
                1,
                "",
                "",
                false
        );

        when(this.usersMapper.isExistMailAddress(any())).thenReturn(true);
        when(this.usersMapper.selectByMailAddress(any())).thenReturn(user);
        when(this.passwordResetTokensMapper.insert(any())).thenReturn(1);

        this.resetPasswordSendService.execute(resetPasswordSendForm);

        verify(this.usersMapper).isExistMailAddress(any());
        verify(this.usersMapper).selectByMailAddress(any());
        verify(this.passwordResetTokensMapper).insert(any());
    }
}
