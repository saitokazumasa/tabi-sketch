package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Day;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Stream;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaysMapperTest {
    @Autowired
    private IDaysMapper daysMapper;

    @ParameterizedTest
    @MethodSource("sampleDay")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
    })
    public void INSERTできるか(final Day day) {
        final var result = this.daysMapper.insert(day);
        assert result == 1;
        assert day.getId() != -1;
    }

    @ParameterizedTest
    @MethodSource("samplePlanId")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
    })
    public void SELECTできるか(final int planId) {
        final var dayList = this.daysMapper.selectByPlanId(planId);
        assert dayList != null;
        assert !dayList.isEmpty();
    }

    private static Stream<Day> sampleDay() {
        final var day = Day.generate(1, 1, 0, "0000");
        return Stream.of(day);
    }

    private static Stream<Integer> samplePlanId() {
        final var planId = 1;
        return Stream.of(planId);
    }
}
