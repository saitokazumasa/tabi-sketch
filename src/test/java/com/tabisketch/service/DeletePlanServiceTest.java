package com.tabisketch.service;

import com.tabisketch.mapper.IDaysMapper;
import com.tabisketch.mapper.IPlacesMapper;
import com.tabisketch.mapper.IPlansMapper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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

    @ParameterizedTest
    @MethodSource("sampleUUID")
    public void 動作するか(final String uuid) {
        this.deletePlanService.execute(uuid);

        verify(this.placesMapper).deleteByPlanUUID(any());
        verify(this.daysMapper).deleteByPlanUUID(any());
        verify(this.plansMapper).deleteByUUID(any());
    }

    private static Stream<String> sampleUUID() {
        final var uuid = UUID.randomUUID().toString();
        return Stream.of(uuid);
    }
}
