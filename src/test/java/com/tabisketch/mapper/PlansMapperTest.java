package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Plan;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Stream;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlansMapperTest {
    @Autowired
    private IPlansMapper plansMapper;

    @ParameterizedTest
    @MethodSource("samplePlan")
    @Sql("classpath:/sql/CreateUser.sql")
    public void INSERTできるか(final Plan plan) {
        final var beforeUUID = plan.getUuid();
        final var result = this.plansMapper.insert(plan);
        assert result == 1;
        assert plan.getId() != -1;
        assert plan.getUuid() != beforeUUID;
    }

    @ParameterizedTest
    @MethodSource("sampleId")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql"
    })
    public void SELECTできるか(final int id) {
        final var planList = this.plansMapper.selectByUserId(id);
        assert planList != null;
        assert !planList.isEmpty();
    }

    private static Stream<Integer> sampleId() {
        final var id = 1;
        return Stream.of(id);
    }

    private static Stream<Plan> samplePlan() {
        final var plan = Plan.generate("", 1);
        return Stream.of(plan);
    }
}
