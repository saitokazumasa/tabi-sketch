package com.tabisketch.mapper;

import com.tabisketch.bean.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IUsersMapper {
    @Select("SELECT * FROM users WHERE email = #{email}")
    User selectByEmail(final String email);
}
