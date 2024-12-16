package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Plan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IPlansMapper {
    @Insert("INSERT INTO plans (title, user_id) VALUES (#{title}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id,uuid")
    void insert(final Plan plan);

    @Select("SELECT * FROM plans WHERE user_id = #{userId}")
    List<Plan> selectByUserId(final int userId);
}
