package com.tabisketch.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CreateUserController.class)
public class CreateUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void 表示できるか() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/create"));
    }
}
