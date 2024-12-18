package com.tabisketch.service;

import com.tabisketch.bean.form.RegisterForm;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RegisterServiceTest {
    @MockBean
    private IUsersMapper usersMapper;
    @MockBean
    private IMAATokensMapper maaTokensMapper;
    @MockBean
    private ISendMailService sendMailService;
    @Autowired
    private IRegisterService registerService;

    @Test
    public void testExecute() throws MessagingException {
        when(this.usersMapper.insert(any())).thenReturn(1);
        when(this.maaTokensMapper.insert(any())).thenReturn(1);

        final var registerForm = new RegisterForm(
                "sample@example.com",
                "password",
                "password"
        );

        this.registerService.execute(registerForm);

        verify(this.usersMapper).insert(any());
        verify(this.maaTokensMapper).insert(any());
        verify(this.sendMailService).execute(any());
    }
}
