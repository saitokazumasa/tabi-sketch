package com.tabisketch.service;

import com.tabisketch.mapper.IPlansMapper;
import com.tabisketch.service.implement.ListPlanService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
public class ListPlanServiceTest {
    @MockBean
    private IPlansMapper plansMapper;

    @ParameterizedTest
    @Sql("")
    @ValueSource(ints = {1})
    public void 動作するか(final int userId) {
        final var listPlanService = new ListPlanService(this.plansMapper);
    }
}
