package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Plan;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;
import java.util.stream.Stream;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlansMapperTest {
    @Autowired
    private IPlansMapper plansMapper;

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

    @ParameterizedTest
    @MethodSource("sampleUUID")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql"
    })
    public void UUIDでSELECTできるか(final UUID uuid) {
        final var plan = this.plansMapper.selectByUUID(uuid);
        assert plan != null;
    }

    @ParameterizedTest
    @MethodSource("sampleUpdatePlan")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql"
    })
    public void UPDATEできるか(final Plan plan) {
        final var result = this.plansMapper.update(plan);
        assert result == 1;
    }

    @ParameterizedTest
    @MethodSource("sampleId")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql"
    })
    public void DELETEできるか(final int id) {
        final var result = this.plansMapper.deleteById(id);
        assert result == 1;
    }

    private static Stream<Plan> sampleUpdatePlan() {
        final var plan = new Plan(
                1,
                UUID.randomUUID(),
                "example",
                1,
                false,
                true
        );
        return Stream.of(plan);
    }

    private static Stream<Integer> sampleId() {
        final var id = 1;
        return Stream.of(id);
    }

    private static Stream<UUID> sampleUUID() {
        final var uuid = UUID.fromString("611d4008-4c0d-4b45-bd1b-21c97e7df3b2");
        return Stream.of(uuid);
    }
}
