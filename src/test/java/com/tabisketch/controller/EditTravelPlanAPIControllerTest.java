package com.tabisketch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.request.CreateTravelPlanAPIRequest;
import com.tabisketch.bean.request.EditTravelPlanAPIRequest;
import com.tabisketch.bean.request.ExampleCreateTravelPlanAPIRequest;
import com.tabisketch.bean.request.ExampleEditTravelPlanAPIRequest;
import com.tabisketch.bean.response.CreateTravelPlanAPIResponse;
import com.tabisketch.bean.response.EditTravelPlanAPIResponse;
import com.tabisketch.exception.InvalidRequestBodyException;
import com.tabisketch.service.IEditTravelPlanService;
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

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@WebMvcTest(EditTravelPlanAPIController.class)
public class EditTravelPlanAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private IEditTravelPlanService service;

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var request = ExampleEditTravelPlanAPIRequest.generate();
        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var json = this.objectMapper.writeValueAsString(request);
        final var response = this.objectMapper.writeValueAsString(new EditTravelPlanAPIResponse(entity));

        when(this.service.execute(entity)).thenReturn(entity);
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/edit/travel-plan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(response));
    }

    @ParameterizedTest
    @WithMockUser
    @MethodSource("validationTestDate")
    public void testValidation(final EditTravelPlanAPIRequest request) throws Exception {
        final var entity = RequestClassUtils.parseToEntityClass(request);
        final var json = this.objectMapper.writeValueAsString(request);

        when(this.service.execute(entity)).thenReturn(entity);
        try {
            this.mockMvc.perform(MockMvcRequestBuilders
                    .post("/api/edit/travel-plan")
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
        final var data1 = new EditTravelPlanAPIRequest(0, "title", LocalDate.now(), true, false);
        final var data2 = new EditTravelPlanAPIRequest(1, "", LocalDate.now(), true, false);
        final var data3 = new EditTravelPlanAPIRequest(1, "title", null, true, false);
        return Stream.of(Arguments.of(data1), Arguments.of(data2), Arguments.of(data3));
    }
}
