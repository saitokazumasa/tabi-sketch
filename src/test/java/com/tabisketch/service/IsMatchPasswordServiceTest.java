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
public class IsMatchPasswordServiceTest {
    @MockBean
    private IUsersMapper usersMapper;
    @Autowired
    private IIsMatchPasswordService isMatchPasswordService;

    @Test
    public void testExecute() {
        final var user = new User(
                -1,
                "sample@example.com",
                "$2a$10$if7oiFZVmP9I59AOtzbSz.dWsdPUUuPTRkcIoR8iYhFpG/0COY.TO",
                false
        );

        when(this.usersMapper.selectByMailAddress(anyString())).thenReturn(user);

        final String truePassword = "password";
        assert this.isMatchPasswordService.execute(user.getMailAddress(), truePassword);
        verify(this.usersMapper).selectByMailAddress(anyString());

        final String falsePassword = "p";
        assert !this.isMatchPasswordService.execute(user.getMailAddress(), falsePassword);
    }
}
