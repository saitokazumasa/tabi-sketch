package com.tabisketch.controller;

import com.tabisketch.bean.form.ResetPasswordForm;
import com.tabisketch.bean.form.ResetPasswordSendForm;
import com.tabisketch.service.implement.ResetPasswordService;
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

@WebMvcTest(ResetPasswordController.class)
public class ResetPasswordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private ResetPasswordService __;

    @Test
    @WithMockUser
    public void testGet() throws Exception {
        final String token = "67da4e4f-a427-4a02-b920-a0d399b75217";
        this.mockMvc.perform(MockMvcRequestBuilders.get("/password-reset/" + token))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("password-reset/index"));
    }

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final String token = "67da4e4f-a427-4a02-b920-a0d399b75217";
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/password-reset/" + token)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }


    @ParameterizedTest
    @MethodSource("validationTestData")
    @WithMockUser
    public void testValidation(final ResetPasswordForm resetPasswordForm) throws Exception {
        final String token = "67da4e4f-a427-4a02-b920-a0d399b75217";
        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/password-reset/" + token)
                        .flashAttr("resetPasswordForm", resetPasswordForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("resetPasswordForm"))
                .andExpect(MockMvcResultMatchers.model().attribute("resetPasswordForm", resetPasswordForm))
                .andExpect(MockMvcResultMatchers.view().name("password-reset/index"));
    }

    private static Stream<ResetPasswordForm> validationTestData() {
        // 両方未入力
        final var resetPasswordForm1 = new ResetPasswordForm("", "");
        // rePasswordが未入力
        final var resetPasswordForm2 = new ResetPasswordForm("password", "");
        // passwordが未入力
        final var resetPasswordForm3 = new ResetPasswordForm("", "password");
        // passwordとrePasswordが異なる
        final var resetPasswordForm4 = new ResetPasswordForm("password", "password1");
        return Stream.of(resetPasswordForm1, resetPasswordForm2, resetPasswordForm3, resetPasswordForm4);
    }
}
