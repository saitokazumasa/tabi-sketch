package com.tabisketch.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(AuthenticateMailController.class)
public class AuthenticateMailControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @ValueSource(strings = {"a8815804-ca73-436b-b1b0-f9c76ccaec90"})
    @WithMockUser
    public void getが動作するか(final String token) {
        try {
            this.mockMvc.perform(MockMvcRequestBuilders.get("/mail/confirm/" + token))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.view().name("mail/confirm"));
        } catch (final Exception e) {
            System.out.println(e.getMessage());
            assert false;
        }
    }
}
