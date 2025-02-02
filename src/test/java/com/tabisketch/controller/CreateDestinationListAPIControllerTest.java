package com.tabisketch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.request.ExampleCreateDestinationListAPIRequest;
import com.tabisketch.bean.response.CreateDestinationListAPIResponse;
import com.tabisketch.service.ICreateDestinationListService;
import com.tabisketch.util.RequestClassUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(CreateDestinationListAPIController.class)
public class CreateDestinationListAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private ICreateDestinationListService service;

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var request = ExampleCreateDestinationListAPIRequest.generate();
        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var json = this.objectMapper.writeValueAsString(request);
        final var response = this.objectMapper.writeValueAsString(new CreateDestinationListAPIResponse(entity));

        when(this.service.execute(entity)).thenReturn(entity);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/create/destination-list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }
}
