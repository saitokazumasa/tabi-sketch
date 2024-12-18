package com.tabisketch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.response.CreatePlanResponse;
import com.tabisketch.bean.form.CreatePlanForm;
import com.tabisketch.service.ICreatePlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CreatePlanAPIController.class)
public class CreatePlanAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private ICreatePlanService __; // DIで使用

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var createPlanForm = new CreatePlanForm("title", "sample@example.com");
        final String responseJson = this.objectMapper.writeValueAsString(CreatePlanResponse.success());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/plan/create")
                        .flashAttr("createPlanForm", createPlanForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
}
