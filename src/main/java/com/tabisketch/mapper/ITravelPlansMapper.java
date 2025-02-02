package com.tabisketch.mapper;

import com.tabisketch.bean.entity.TravelPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface ITravelPlansMapper {
    @Insert("INSERT INTO travel_plans (title, action_date, user_id) " +
            "   VALUES (#{title}, #{actionDate}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id,uuid")
    int insert(final TravelPlan record);
}
