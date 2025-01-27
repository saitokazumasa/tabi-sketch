package com.tabisketch.mapper;

import com.tabisketch.bean.entity.ExampleDay;
import com.tabisketch.bean.entity.ExamplePlace;
import com.tabisketch.bean.entity.ExamplePlan;
import com.tabisketch.bean.entity.Place;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.UUID;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlacesMapperTest {
    @Autowired
    private IPlacesMapper placesMapper;

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
            "classpath:/sql/CreateGooglePlace.sql",
    })
    public void testInsert() {
        final var place = ExamplePlace.generate();
        assert this.placesMapper.insert(place) == 1;
        assert place.getId() != -1;
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
            "classpath:/sql/CreateGooglePlace.sql",
            "classpath:/sql/CreatePlace.sql"
    })
    public void testSelect() {
        final int id = ExamplePlace.generate().getId();
        assert this.placesMapper.selectById(id) != null;

        final int dayId = ExampleDay.generate().getId();
        final var placeList = this.placesMapper.selectByDayId(dayId);
        assert placeList != null;
        assert !placeList.isEmpty();
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
            "classpath:/sql/CreateGooglePlace.sql",
            "classpath:/sql/CreatePlace.sql"
    })
    public void testUpdate() {
        final var place = ExamplePlace.generate();
        place.setBudget(2);
        assert this.placesMapper.update(place) == 1;
    }

    @Test
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
            "classpath:/sql/CreateGooglePlace.sql",
            "classpath:/sql/CreatePlace.sql"
    })
    public void testDelete() {
        final var uuid = ExamplePlan.generate().getUuid();
        assert this.placesMapper.deleteByPlanUUID(uuid) >= 1;
    }
}
