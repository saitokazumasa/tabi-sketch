//package com.tabisketch.mapper;
//
//import com.tabisketch.bean.entity.ExampleGooglePlace;
//import com.tabisketch.bean.entity.GooglePlace;
//import org.junit.jupiter.api.Test;
//import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.test.context.jdbc.Sql;
//
//@MybatisTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//public class GooglePlaceMapperTest {
//    @Autowired
//    private IGooglePlaceMapper googlePlaceMapper;
//
//    @Test
//    public void testInsert() {
//        final var googlePlace = ExampleGooglePlace.generate();
//        assert this.googlePlaceMapper.insert(googlePlace) == 1;
//        assert googlePlace.getId() != -1;
//    }
//
//    @Test
//    @Sql({
//            "classpath:/sql/CreateGooglePlace.sql"
//    })
//    public void testSelect() {
//        final var placeId = ExampleGooglePlace.generate().getPlaceId();
//        final var googlePlace = this.googlePlaceMapper.selectByPlaceId(placeId);
//        assert googlePlace != null;
//    }
//}
