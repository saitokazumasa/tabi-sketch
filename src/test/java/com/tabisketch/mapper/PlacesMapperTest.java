package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Place;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalTime;
import java.util.UUID;
import java.util.stream.Stream;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PlacesMapperTest {
    @Autowired
    private IPlacesMapper placesMapper;

    @ParameterizedTest
    @MethodSource("samplePlace")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
            "classpath:/sql/CreateGooglePlace.sql",
    })
    public void INSERTできるか(final Place place) {
        final var result = this.placesMapper.insert(place);
        assert result == 1;
        assert place.getId() != -1;
    }

    @ParameterizedTest
    @MethodSource("sampleDayId")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
            "classpath:/sql/CreateGooglePlace.sql",
            "classpath:/sql/CreatePlace.sql"
    })
    public void SELECTできるか(final int dayId) {
        final var placeList = this.placesMapper.selectByDayId(dayId);
        assert placeList != null;
        assert !placeList.isEmpty();
    }

    @ParameterizedTest
    @MethodSource("samplePlanUUID")
    @Sql({
            "classpath:/sql/CreateUser.sql",
            "classpath:/sql/CreatePlan.sql",
            "classpath:/sql/CreateDay.sql",
            "classpath:/sql/CreateGooglePlace.sql",
            "classpath:/sql/CreatePlace.sql"
    })
    public void DELETEできるか(final UUID uuid) {
        final var result = this.placesMapper.deleteByPlanUUID(uuid);
        assert result >= 1;
    }

    private static Stream<Integer> sampleDayId() {
        final var dayId = 1;
        return Stream.of(dayId);
    }

    private static Stream<UUID> samplePlanUUID() {
        final var planUUID = UUID.fromString("611d4008-4c0d-4b45-bd1b-21c97e7df3b2");
        return Stream.of(planUUID);
    }

    private static Stream<Place> samplePlace() {
        final var startTime = LocalTime.of(10, 0);
        final var endTime = LocalTime.of(10, 0);
        final var place = Place.generate(
                1, 1, 0,
                startTime, endTime, null, null,
                null, null, null
        );
        return Stream.of(place);
    }
}
