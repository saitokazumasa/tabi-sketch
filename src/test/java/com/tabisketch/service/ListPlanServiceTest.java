package com.tabisketch.service;

import com.tabisketch.bean.entity.Plan;
import com.tabisketch.bean.entity.User;
import com.tabisketch.mapper.IPlansMapper;
import com.tabisketch.mapper.IUsersMapper;
import com.tabisketch.service.implement.ListPlanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class ListPlanServiceTest {
    @MockBean
    private IUsersMapper usersMapper;
    @MockBean
    private IPlansMapper plansMapper;

    @Test
    @WithMockUser(username = "sample@example.com")
    public void 動作するか() {
        final var mail = getCurrentMail();
        when(this.usersMapper.selectByMail(mail)).thenReturn(User.generate("sample@example.com", "password"));
        when(this.plansMapper.selectByUserId(-1)).thenReturn(new ArrayList<Plan>());

        final var listPlanService = new ListPlanService(this.usersMapper, this.plansMapper);
        final List<Plan> planList = listPlanService.execute(mail);
        assert planList != null;
    }

    private String getCurrentMail() {
        return SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
    }
}
