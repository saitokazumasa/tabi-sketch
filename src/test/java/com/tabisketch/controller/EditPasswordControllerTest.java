package com.tabisketch.controller;

import com.tabisketch.bean.form.EditPasswordForm;
import com.tabisketch.bean.form.ExampleEditPasswordForm;
import com.tabisketch.service.IEditPasswordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(EditPasswordController.class)
public class EditPasswordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private IEditPasswordService __; // DIで使用

    @Test
    @WithMockUser(username = "sample@example.com")
    public void testGet() throws Exception {
        final var editPasswordForm = EditPasswordForm.empty();
        editPasswordForm.setMailAddress(currentUserMailAddress());

        this.mockMvc.perform(MockMvcRequestBuilders.get("/user/edit/password"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("editPasswordForm"))
                .andExpect(MockMvcResultMatchers.model().attribute("editPasswordForm", editPasswordForm))
                .andExpect(MockMvcResultMatchers.view().name("user/edit/password/index"));
    }

    @Test
    @WithMockUser(username = "sample@example.com", password = "$2a$10$G7Emd1ALL6ibttkgRZtBZeX6Qps6lgEGKq.njouwtiuE4uvjD2YMO")
    public void testPost() throws Exception {
        final var editPasswordForm = ExampleEditPasswordForm.generate();

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/user/edit/password")
                        .flashAttr("editPasswordForm", editPasswordForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.model().hasNoErrors())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/user/edit/password/confirm"));
    }

    private static String currentUserMailAddress() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
