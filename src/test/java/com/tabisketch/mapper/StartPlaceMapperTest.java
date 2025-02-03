package com.tabisketch.mapper;

import com.tabisketch.bean.entity.ExampleStartPlace;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StartPlaceMapperTest {
    @Autowired
    private IStartPlacesMapper mapper;

    @Test
    @Sql({
            "classpath:/sql/InsertExampleUser.sql",
            "classpath:/sql/InsertExampleTravelPlan.sql",
            "classpath:/sql/InsertExampleDestinationList.sql"
    })
    public void testInsert() {
        final var entity = ExampleStartPlace.generate();
        assert this.mapper.insert(entity) == 1;
    }

    @Test
    @Sql({
            "classpath:/sql/InsertExampleUser.sql",
            "classpath:/sql/InsertExampleTravelPlan.sql",
            "classpath:/sql/InsertExampleDestinationList.sql",
            "classpath:/sql/InsertExampleStartPlace.sql"
    })
    public void testSelect() {
        final var entity = ExampleStartPlace.generate();
        assert this.mapper.selectById(entity.getId()) != null;
    }
}
