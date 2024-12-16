package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Day;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IDaysMapper {
    @Insert("INSERT INTO days (day, plan_id, walk_threshold, prefer_transportation_list_binary) " +
            "VALUES (#{day}, #{planId}, #{walkThreshold}, #{preferTransportationListBinary})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final Day day);

    @Select("SELECT * FROM days WHERE plan_id = #{planId}")
    List<Day> selectByPlanId(final int planId);
}
