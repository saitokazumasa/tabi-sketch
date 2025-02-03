package com.tabisketch.mapper;

import com.tabisketch.bean.entity.ExampleTravelPlan;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TravelPlanMapperTest {
    @Autowired
    private ITravelPlansMapper mapper;

    @Test
    @Sql("classpath:/sql/InsertExampleUser.sql")
    public void testInsert() {
        final var entity = ExampleTravelPlan.generate();
        assert this.mapper.insert(entity) == 1;
    }

    @Test
    @Sql({
            "classpath:/sql/InsertExampleUser.sql",
            "classpath:/sql/InsertExampleTravelPlan.sql"
    })
    public void testSelect() {
        final var entity = ExampleTravelPlan.generate();
        assert this.mapper.selectById(entity.getId()) != null;
    }

    @Test
    @Sql({
            "classpath:/sql/InsertExampleUser.sql",
            "classpath:/sql/InsertExampleTravelPlan.sql"
    })
    public void testUpdate() {
        final var entity = ExampleTravelPlan.generate();
        entity.setTitle("edited");
        entity.setEditable(false);
        entity.setPubliclyViewable(true);
        assert this.mapper.update(entity) == 1;
    }
}
