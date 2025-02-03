package com.tabisketch.mapper;

import com.tabisketch.bean.entity.TravelPlan;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ITravelPlansMapper {
    @Insert("INSERT INTO travel_plans (action_date, user_id) " +
            "   VALUES (#{actionDate}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id,uuid")
    int insert(final TravelPlan record);

    @Select("SELECT * FROM travel_plans WHERE id = #{id}")
    TravelPlan selectById(final int id);

    @Update("UPDATE travel_plans SET " +
            "   title = #{title}, " +
            "   action_date = #{actionDate}, " +
            "   editable = #{editable}, " +
            "   publicly_viewable = #{publiclyViewable} " +
            "WHERE id = #{id}")
    int update(final TravelPlan record);
}
