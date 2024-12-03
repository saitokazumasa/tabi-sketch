package com.tabisketch.controller;

import com.tabisketch.bean.entity.Plan;
import com.tabisketch.service.IListPlanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@WebMvcTest(ListPlanController.class)
public class ListPlanControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IListPlanService listPlanService; // IDで使用している

    @Test
    @WithMockUser(username = "sample@example.com")
    public void getが動作するか() throws Exception {
        final String mail = getCurrentMail();
        when(listPlanService.execute(mail)).thenReturn(new ArrayList<Plan>());

        mockMvc.perform(MockMvcRequestBuilders.get("/plan/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("planList"))
                .andExpect(MockMvcResultMatchers.model().attribute("planList", new ArrayList<Plan>()))
                .andExpect(MockMvcResultMatchers.view().name("plan/list"));
    }

    private String getCurrentMail() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}