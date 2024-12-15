package com.tabisketch.service;

import com.tabisketch.bean.entity.Plan;
import com.tabisketch.mapper.IPlansMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DeletePlanServiceTest {
    @MockBean
    private IPlansMapper plansMapper;
    @Autowired
    private IDeletePlanService deletePlanService;

    @ParameterizedTest
    @MethodSource("sampleUUID")
    public void 動作するか(final String uuid) {
        when(this.plansMapper.selectByUUID(any())).thenReturn(Plan.generate("", 1));
        when(this.plansMapper.deleteById(anyInt())).thenReturn(1);

        final var result = this.deletePlanService.execute(uuid);

        assert result;
        verify(this.plansMapper).selectByUUID(any());
        verify(this.plansMapper).deleteById(anyInt());
    }

    private static Stream<String> sampleUUID() {
        final var uuid = UUID.randomUUID().toString();
        return Stream.of(uuid);
    }
}
