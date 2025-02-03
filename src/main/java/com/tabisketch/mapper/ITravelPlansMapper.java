package com.tabisketch.mapper;

import com.tabisketch.bean.entity.TravelPlan;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ITravelPlansMapper {
    @Insert("INSERT INTO travel_plans (user_id) " +
            "   VALUES (#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final TravelPlan record);

    @Select("SELECT * FROM travel_plans WHERE id = #{id}")
    TravelPlan selectById(final int id);

    @Update("UPDATE travel_plans SET " +
            "   title = #{title}, " +
            "   editable = #{editable}, " +
            "   publicly_viewable = #{publiclyViewable} " +
            "WHERE id = #{id}")
    int update(final TravelPlan record);
}
