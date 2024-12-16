package com.tabisketch.service;

import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IUsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ExistMailAddressServiceTest {
    @MockBean
    private IUsersMapper usersMapper;
    @Autowired
    private IExistMailAddressService existMailAddressService;

    @Test
    public void testExecute() {
        final var user = new User(
                -1,
                "sample@example.com",
                "",
                false
        );

        when(this.usersMapper.selectByMailAddress(anyString())).thenReturn(user);

        assert this.existMailAddressService.execute(user.getMailAddress());

        verify(this.usersMapper).selectByMailAddress(anyString());
    }
}
