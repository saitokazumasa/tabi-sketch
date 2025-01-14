package com.tabisketch.service;

import com.tabisketch.bean.entity.User;
import com.tabisketch.bean.form.EditPasswordForm;
import com.tabisketch.exception.UpdateFailedException;
import com.tabisketch.mapper.IUsersMapper;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EditPasswordServiceTest {
    @MockitoBean
    private IUsersMapper usersMapper;
    @MockitoBean
    private ISendMailService sendMailService;
    @Autowired
    private IEditPasswordService editPasswordService;

    @Test
    @WithMockUser
    public void testExecute() throws MessagingException, UpdateFailedException {
        final var user =
                User.generate("", "$2a$10$if7oiFZVmP9I59AOtzbSz.dWsdPUUuPTRkcIoR8iYhFpG/0COY.TO");
        final var editPasswordForm = new EditPasswordForm(
                "sample@example.com",
                "password",
                "password2",
                "password2"
        );

        when(this.usersMapper.selectByMailAddress(anyString())).thenReturn(user);
        when(this.usersMapper.update(any())).thenReturn(1);

        this.editPasswordService.execute(editPasswordForm);

        verify(this.usersMapper).selectByMailAddress(anyString());
        verify(this.usersMapper).update(any());
        verify(this.sendMailService).execute(any());
    }
}
