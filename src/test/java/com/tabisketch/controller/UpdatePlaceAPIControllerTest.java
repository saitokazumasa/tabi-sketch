package com.tabisketch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.form.UpdatePlaceForm;
import com.tabisketch.bean.response.UpdatePlaceResponse;
import com.tabisketch.service.IUpdatePlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalTime;

@WebMvcTest(UpdatePlaceAPIController.class)
public class UpdatePlaceAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private IUpdatePlaceService __; // DIで使用

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var updatePlaceForm = new UpdatePlaceForm(
                1,
                1,
                0,
                LocalTime.of(10, 0),
                LocalTime.of(11,0),
                null,
                null,
                null,
                null,
                null
        );
        final String responseJson = this.objectMapper.writeValueAsString(UpdatePlaceResponse.success());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/update-place")
                        .flashAttr("updatePlaceForm", updatePlaceForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
}
