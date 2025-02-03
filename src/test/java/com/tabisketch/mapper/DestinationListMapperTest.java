package com.tabisketch.mapper;

import com.tabisketch.bean.entity.ExampleDestinationList;
import com.tabisketch.bean.entity.ExampleTravelPlan;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DestinationListMapperTest {
    @Autowired
    private IDestinationListsMapper mapper;

    @Test
    @Sql({
            "classpath:/sql/InsertExampleUser.sql",
            "classpath:/sql/InsertExampleTravelPlan.sql"
    })
    public void testInsert() {
        final var entity = ExampleDestinationList.generate();
        assert this.mapper.insert(entity) == 1;
    }

    @Test
    @Sql({
            "classpath:/sql/InsertExampleUser.sql",
            "classpath:/sql/InsertExampleTravelPlan.sql",
            "classpath:/sql/InsertExampleDestinationList.sql"
    })
    public void testSelect() {
        final var entity = ExampleTravelPlan.generate();
        assert this.mapper.selectById(entity.getId()) != null;
    }

    @Test
    @Sql({
            "classpath:/sql/InsertExampleUser.sql",
            "classpath:/sql/InsertExampleTravelPlan.sql",
            "classpath:/sql/InsertExampleDestinationList.sql"
    })
    public void testUpdate() {
        final var entity = ExampleDestinationList.generate();
        entity.setAvailableTransportationListBinary("0000");
        assert this.mapper.update(entity) == 1;
    }
}
