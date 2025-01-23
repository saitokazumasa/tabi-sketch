package com.tabisketch.controller;

import com.tabisketch.bean.form.ResetPasswordSendForm;
import com.tabisketch.service.implement.ResetPasswordSendService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

@WebMvcTest(ResetPasswordSendController.class)
public class ResetPasswordSendControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ResetPasswordSendService __;

    @Test
    @WithMockUser
    public void testGet() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/password-reset"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password-reset/send"));
    }

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final String currentMailAddress = "sample@example.com";

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/password-reset")
                        .flashAttr("currentMailAddress", currentMailAddress)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("password-reset/send"));
    }

    @ParameterizedTest
    @MethodSource("validationTestData")
    @WithMockUser
    public void testValidation(final ResetPasswordSendForm resetPasswordSendForm) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/password-reset")
                        .flashAttr("currentMailAddress", resetPasswordSendForm.getCurrentMailAddress())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("resetPasswordSendForm"))
                .andExpect(MockMvcResultMatchers.model().attribute("currentMailAddress", resetPasswordSendForm.getCurrentMailAddress()))
                .andExpect(MockMvcResultMatchers.view().name("password-reset/send"));
    }

    private static Stream<ResetPasswordSendForm> validationTestData() {
        // currentMailAddressが未入力
        final var resetPasswordSendForm1 = new ResetPasswordSendForm("");
        return Stream.of(resetPasswordSendForm1);
    }

    @Test
    @WithMockUser
    public void testSend() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/password-reset/send"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/password-reset/send"));
    }
}
