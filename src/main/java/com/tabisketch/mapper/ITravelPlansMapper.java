package com.tabisketch.mapper;

import com.tabisketch.bean.entity.TravelPlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ITravelPlansMapper {
    @Insert("INSERT INTO travel_plans (title, action_date, user_id) " +
            "   VALUES (#{title}, #{actionDate}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id,uuid")
    int insert(final TravelPlan record);

    @Update("UPDATE travel_plans SET " +
            "   title = #{title}, " +
            "   action_date = #{actionDate}, " +
            "   editable = #{editable}, " +
            "   publicly_viewable = #{publiclyViewable} " +
            "WHERE id = #{id}")
    int update(final TravelPlan record);
}
