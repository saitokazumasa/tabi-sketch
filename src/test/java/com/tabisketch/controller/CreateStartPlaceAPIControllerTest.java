package com.tabisketch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.request.CreateStartPlaceAPIRequest;
import com.tabisketch.bean.request.ExampleCreateStartPlaceAPIRequest;
import com.tabisketch.bean.response.CreateStartPlaceAPIResponse;
import com.tabisketch.exception.InvalidRequestBodyException;
import com.tabisketch.service.ICreateStartPlaceService;
import com.tabisketch.util.RequestClassUtils;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@WebMvcTest(CreateStartPlaceAPIController.class)
public class CreateStartPlaceAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private ICreateStartPlaceService service;

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var request = ExampleCreateStartPlaceAPIRequest.generate();
        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var json = this.objectMapper.writeValueAsString(request);
        final var response = this.objectMapper.writeValueAsString(new CreateStartPlaceAPIResponse(entity));

        when(this.service.execute(entity)).thenReturn(entity);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/create/start-place")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @ParameterizedTest
    @WithMockUser
    @MethodSource("validationTestDate")
    public void testValidation(final CreateStartPlaceAPIRequest request) throws Exception {
        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var json = this.objectMapper.writeValueAsString(request);

        when(this.service.execute(entity)).thenReturn(entity);
        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/create/start-place")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json)
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
            );
        } catch (final InvalidRequestBodyException | ServletException e) {
            System.out.println(e.getMessage());
            assert true;
            return;
        }
        assert false;
    }

    private static Stream<Arguments> validationTestDate() {
        final var data1 = new CreateStartPlaceAPIRequest("", LocalDateTime.now(), 1);
        final var data2 = new CreateStartPlaceAPIRequest("placeId", null, 1);
        final var data3 = new CreateStartPlaceAPIRequest("placeId", LocalDateTime.now(), 0);
        return Stream.of(Arguments.of(data1), Arguments.of(data2), Arguments.of(data3));
    }
}
