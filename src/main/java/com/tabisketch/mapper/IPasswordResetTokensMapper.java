package com.tabisketch.mapper;

import com.tabisketch.bean.entity.PasswordResetToken;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IPasswordResetTokensMapper {
    @Insert("INSERT INTO password_reset_tokens (token, user_id) VALUES (#{token}, #{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final PasswordResetToken passwordResetToken);

    @Select("SELECT * FROM password_reset_tokens WHERE token = #{token}")
    PasswordResetToken selectByToken(final String token);
}
