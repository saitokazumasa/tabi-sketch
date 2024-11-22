package com.tabisketch.controller;

import com.tabisketch.bean.form.CreateUserForm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.stream.Stream;

@WebMvcTest(CreateUserController.class)
public class CreateUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void getが動作するか() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/create"));
    }

    @ParameterizedTest
    @MethodSource("ProvideForms")
    @WithMockUser
    public void postが動作するか(final CreateUserForm createUserForm) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create")
                        .flashAttr("createUserForm", createUserForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/create/confirm"));
    }

    public static Stream<CreateUserForm> ProvideForms() {
        final var f1 = new CreateUserForm("example@mail.com", "password", "password");
        return Stream.of(f1);
    }

    @ParameterizedTest
    @MethodSource("ProvideEmptyForms")
    @WithMockUser
    public void フォームがバリデーションエラーになるか(final CreateUserForm createUserForm) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/create")
                        .flashAttr("createUserForm", createUserForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().hasErrors())
                .andExpect(MockMvcResultMatchers.model().attributeExists("createUserForm"))
                .andExpect(MockMvcResultMatchers.model().attribute("createUserForm", createUserForm))
                .andExpect(MockMvcResultMatchers.view().name("user/create"));
    }

    private static Stream<CreateUserForm> ProvideEmptyForms() {
        final var f1 = new CreateUserForm();
        final var f2 = new CreateUserForm("", "password", "password");
        final var f3 = new CreateUserForm("example@mail.com", "", "password");
        final var f4 = new CreateUserForm("example@mail.com", "password", "");
        final var f5 = new CreateUserForm("example@mail.com", "password", "pass");
        return Stream.of(f1, f2, f3, f4, f5);
    }

    @Test
    @WithMockUser
    public void confirmが動作するか() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/create/confirm"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("user/create-confirm"));
    }
}
