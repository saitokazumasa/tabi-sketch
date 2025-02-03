package com.tabisketch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.request.EditDestinationListAPIRequest;
import com.tabisketch.bean.request.ExampleEditDestinationListAPIRequest;
import com.tabisketch.bean.response.EditDestinationListAPIResponse;
import com.tabisketch.exception.InvalidRequestBodyException;
import com.tabisketch.service.IEditDestinationListService;
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

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@WebMvcTest(EditDestinationListAPIController.class)
public class EditDestinationListAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private IEditDestinationListService service;

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var request = ExampleEditDestinationListAPIRequest.generate();
        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var json = this.objectMapper.writeValueAsString(request);
        final var response = this.objectMapper.writeValueAsString(new EditDestinationListAPIResponse(entity));

        when(this.service.execute(entity)).thenReturn(entity);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/edit/destination-list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @ParameterizedTest
    @WithMockUser
    @MethodSource("validationTestDate")
    public void testValidation(final EditDestinationListAPIRequest request) throws Exception {
        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var json = this.objectMapper.writeValueAsString(request);

        when(this.service.execute(entity)).thenReturn(entity);
        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/edit/destination-list")
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
        final var data1 = new EditDestinationListAPIRequest(0, "1111");
        final var data2 = new EditDestinationListAPIRequest(1, "111");
        final var data3 = new EditDestinationListAPIRequest(1, "11111");
        return Stream.of(Arguments.of(data1), Arguments.of(data2), Arguments.of(data3));
    }
}
