package com.tabisketch.service;

import com.tabisketch.mapper.IDaysMapper;
import com.tabisketch.mapper.IPlacesMapper;
import com.tabisketch.mapper.IPlansMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeletePlanServiceTest {
    @MockBean
    private IPlansMapper plansMapper;
    @MockBean
    private IDaysMapper daysMapper;
    @MockBean
    private IPlacesMapper placesMapper;
    @Autowired
    private IDeletePlanService deletePlanService;

    @Test
    public void testExecute() {
        when(this.plansMapper.deleteByUUID(any())).thenReturn(1);

        final var uuid = UUID.randomUUID().toString();

        this.deletePlanService.execute(uuid);

        verify(this.placesMapper).deleteByPlanUUID(any());
        verify(this.daysMapper).deleteByPlanUUID(any());
        verify(this.plansMapper).deleteByUUID(any());
    }
}
