package com.tabisketch.controller;

import com.tabisketch.bean.form.EditMailForm;
import com.tabisketch.bean.form.IsMatchPasswordForm;
import com.tabisketch.service.IIsMatchPasswordService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;

@WebMvcTest(SendEditMailController.class)
public class SendEditMailControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IIsMatchPasswordService isMatchPasswordService;

    @Test
    @WithMockUser
    public void getが動作するか() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/edit/mail"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/edit/mail/index"));
    }

    @ParameterizedTest
    @MethodSource("postが動作するかのテストデータ")
    @WithMockUser(password = "$2a$10$G7Emd1ALL6ibttkgRZtBZeX6Qps6lgEGKq.njouwtiuE4uvjD2YMO")
    public void postが動作するか(final EditMailForm editMailForm) throws Exception {
        final var isMatchPasswordForm = new IsMatchPasswordForm();
        when(this.isMatchPasswordService.execute(isMatchPasswordForm)).thenReturn(true);

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/edit/mail")
                        .flashAttr("editMailForm", editMailForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/edit/mail/send"));
    }

    private static Stream<EditMailForm> postが動作するかのテストデータ() {
        final var e1 = new EditMailForm("sample@example.com", "password");
        return Stream.of(e1);
    }

    @ParameterizedTest
    @ValueSource(strings = {"sample2@example.com"})
    @WithMockUser
    public void sendが動作するか(final String mail) throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/user/edit/mail/send")
                        .flashAttr("mail", mail)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/edit/mail/send"));
    }
}
