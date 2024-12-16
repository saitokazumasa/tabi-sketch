package com.tabisketch.service;

import com.tabisketch.bean.entity.MAAToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthMailAddressServiceTest {
    @MockBean
    private IMAATokensMapper maaTokensMapper;
    @MockBean
    private IUsersMapper usersMapper;
    @Autowired
    private IAuthMailAddressService authMailAddressService;

    @Test
    public void testExecute() {
        final var maaToken = new MAAToken(
                1,
                UUID.randomUUID(),
                "",
                1,
                LocalDateTime.now()
        );
        final var user = new User(1, "", "", false);

        when(this.maaTokensMapper.selectByToken(any())).thenReturn(maaToken);
        when(this.usersMapper.selectById(anyInt())).thenReturn(user);

        this.authMailAddressService.execute(maaToken.getToken().toString());

        verify(this.maaTokensMapper).selectByToken(any());
        verify(this.usersMapper).selectById(anyInt());
        verify(this.usersMapper).update(any());
        verify(this.maaTokensMapper).delete(anyInt());
    }
}
