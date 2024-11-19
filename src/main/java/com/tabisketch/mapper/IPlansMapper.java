package com.tabisketch.mapper;

import com.tabisketch.bean.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IPlansMapper {
    @Select("SELECT * FROM plans WHERE user_id = #{userId}")
    public List<Plan> selectByUserId(final int userId);
}
