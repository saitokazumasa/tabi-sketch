package com.tabisketch.security;

import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IUsersMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LoginUserDetailsServiceTest {

    @Data
    @AllArgsConstructor
    private static class TestData {
        private String mail;
        private User user;
    }

    @MockBean
    private IUsersMapper usersMapper;

    @Test
    public void ログインできるか() {
        final var testData = generateTestData();
        for (final var data : testData) {
            when(usersMapper.selectByMail(data.getMail())).thenReturn(data.getUser());

            final var service = new LoginUserDetailsService(usersMapper);
            final var userDetails = service.loadUserByUsername(data.getMail());

            verify(usersMapper).selectByMail(data.getMail());
            assert userDetails != null;
            assert userDetails.getUsername().equals(data.getMail());
        }
    }

    private TestData[] generateTestData() {
        return new TestData[] {
                new TestData("sample@mail.com", new User(0, "sample@mail.com", "password"))
        };
    }
}
