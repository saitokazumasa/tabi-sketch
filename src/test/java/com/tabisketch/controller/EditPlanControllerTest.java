package com.tabisketch.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;
import java.util.stream.Stream;

@WebMvcTest(EditPlanController.class)
public class EditPlanControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("samplePlanUUID")
    @WithMockUser
    public void getが動作するか(final String planUUID) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/plan/"+ planUUID + "/edit"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("plan/edit"));
    }

    private static Stream<String> samplePlanUUID() {
        final var uuid = UUID.randomUUID().toString();
        return Stream.of(uuid);
    }
}
