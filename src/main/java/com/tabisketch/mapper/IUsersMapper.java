package com.tabisketch.mapper;

import com.tabisketch.bean.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUsersMapper {
    @Select("SELECT * FROM users WHERE mail = #{mail}")
    User selectByMail(final String mail);
}
