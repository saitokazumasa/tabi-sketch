package com.tabisketch.service;

import com.tabisketch.bean.entity.MAAToken;
import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IMAATokensMapper;
import com.tabisketch.mapper.IUsersMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Stream;

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

    @ParameterizedTest
    @MethodSource("sampleMailAddressAuthToken")
    public void 動作するか(final MAAToken maaToken) {
        when(this.maaTokensMapper.selectByToken(any())).thenReturn(maaToken);
        when(this.usersMapper.selectById(anyInt())).thenReturn(new User(1, "", "", false));

        assert this.authMailAddressService.execute(maaToken.getToken().toString());
        verify(this.maaTokensMapper).selectByToken(any());
        verify(this.usersMapper).update(any());
//        verify(this.maaTokensMapper).deleteById(anyInt());
    }

    private static Stream<MAAToken> sampleMailAddressAuthToken() {
        final var mailAddressAuthToken = new MAAToken(
                1,
                UUID.randomUUID(),
                "",
                1,
                LocalDateTime.now()
        );
        return Stream.of(mailAddressAuthToken);
    }

    private static Stream<MAAToken> sampleMailAddressAuthTokenWithNewMailAddress() {
        final var mailAddressAuthToken = new MAAToken(
                1,
                UUID.randomUUID(),
                "sample@example.com",
                1,
                LocalDateTime.now()
        );
        return Stream.of(mailAddressAuthToken);
    }
}
