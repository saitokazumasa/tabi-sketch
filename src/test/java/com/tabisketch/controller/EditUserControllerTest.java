package com.tabisketch.controller;

import com.tabisketch.mapper.IUsersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EditUserController.class)
public class EditUserControllerTest {
    @MockBean
    private IUsersMapper usersMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "sample@example.com")
    public void getが動作するか() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/edit/index"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("user"));
    }
    @Test
    @WithMockUser(username = "sample@example.com")
    public void viewが正しく返るか() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/edit"))
                .andExpect(MockMvcResultMatchers.view().name("user/edit/index"));
    }
}
