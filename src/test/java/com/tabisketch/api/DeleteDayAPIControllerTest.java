package com.tabisketch.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tabisketch.bean.form.ExampleDeleteDayForm;
import com.tabisketch.bean.response.implement.DeleteDayResponse;
import com.tabisketch.service.IDeleteDayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DeleteDayAPIControler.class)
public class DeleteDayAPIControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockitoBean
    private IDeleteDayService __; // DIで使用

    @Test
    @WithMockUser
    public void testPost() throws Exception {
        final var deleteDayForm = ExampleDeleteDayForm.generate();
        final String responseJson = this.objectMapper.writeValueAsString(DeleteDayResponse.success());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/delete-day")
                        .flashAttr("deleteDayForm", deleteDayForm)
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(responseJson));
    }
}
