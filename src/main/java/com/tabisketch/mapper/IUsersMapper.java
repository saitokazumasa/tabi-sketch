package com.tabisketch.mapper;

import com.tabisketch.bean.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IUsersMapper {
    @Insert("INSERT INTO users (mail, password) VALUES (#{mail}, #{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final User user);

    @Select("SELECT * FROM users WHERE mail = #{mail}")
    User selectByMail(final String mail);

    @Update("")
    int updateMailVerified(final boolean isMailVerified);
}
