package com.tabisketch.controller;

import com.tabisketch.service.IDeletePlanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;
import java.util.stream.Stream;

@WebMvcTest(DeletePlanController.class)
public class DeletePlanControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IDeletePlanService __; // DIで使用している

    @ParameterizedTest
    @MethodSource("sampleUUID")
    @WithMockUser
    public void deleteが動作するか(final String sampleUUID) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                    .delete("/share/" + sampleUUID)
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/plan/list"));
    }

    private static Stream<String> sampleUUID() {
        final var uuid = UUID.randomUUID().toString();
        return Stream.of(uuid);
    }
}
