package com.tabisketch.mapper;

import com.tabisketch.bean.entity.Plan;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.UUID;

@Mapper
public interface IPlansMapper {
    @Select("SELECT * FROM plans WHERE user_id = #{userId}")
    List<Plan> selectByUserId(final int userId);

    @Select("SELECT * FROM plans WHERE uuid = #{uuid}")
    Plan selectByUUID(final UUID uuid);

    @Update("UPDATE plans " +
            "SET title = #{title}, is_editable = #{isEditable}, is_public = #{isPublic} " +
            "WHERE id = #{id}")
    int update(final Plan plan);

    @Delete("DELETE FROM plans WHERE id = #{id}")
    int deleteById(final int id);
}
