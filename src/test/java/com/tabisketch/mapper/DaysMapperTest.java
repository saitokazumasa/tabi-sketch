package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Day;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaysMapperTest {
    @Autowired
    private IDaysMapper daysMapper;

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
    })
    public void testInsert() {
        final var day = Day.generate(
                -1,
                1,
                1,
                "0000"
        );
        this.daysMapper.insert(day);
        assert day.getId() != -1;
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
    })
    public void testSelect() {
        final int planId = 1;
        final var dayList = this.daysMapper.selectByPlanId(planId);
        assert dayList != null;
        assert !dayList.isEmpty();
    }
}
