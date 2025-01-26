package com.tabisketch.controller;

import com.tabisketch.bean.form.ExampleSendResetPasswordForm;
import com.tabisketch.bean.form.SendResetPasswordForm;
import com.tabisketch.service.implement.SendResetPasswordService;
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

@WebMvcTest(SendResetPasswordController.class)
public class SendResetPasswordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private SendResetPasswordService __;

    @Test
    @WithMockUser
    public void testGet() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/password-reset"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password-reset/index"));
    }

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var sendResetPasswordForm = ExampleSendResetPasswordForm.generate();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/password-reset")
                        .flashAttr("sendResetPasswordForm", sendResetPasswordForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("password-reset/send"));
    }

    @ParameterizedTest
    @MethodSource("validationTestData")
    @WithMockUser
    public void testValidation(final SendResetPasswordForm sendResetPasswordForm) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/password-reset")
                        .flashAttr("mailAddress", sendResetPasswordForm.getMailAddress())
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("sendResetPasswordForm"))
                .andExpect(MockMvcResultMatchers.model().attribute("sendResetPasswordForm", sendResetPasswordForm))
                .andExpect(MockMvcResultMatchers.view().name("password-reset/index"));
    }

    private static Stream<SendResetPasswordForm> validationTestData() {
        // mailAddressが未入力
        final var resetPasswordSendForm1 = new SendResetPasswordForm("");
        return Stream.of(resetPasswordSendForm1);
    }

    @Test
    @WithMockUser
    public void testSend() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/password-reset/send"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password-reset/send"));
    }
}
