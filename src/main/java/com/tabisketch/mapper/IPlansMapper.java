package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Plan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IPlansMapper {
    @Select("SELECT * FROM plans WHERE user_id = #{userId}")
    List<Plan> selectByUserId(final int userId);

    @Delete("DELETE FROM plans WHERE id = #{id}")
    int deleteById(final int id);
}
