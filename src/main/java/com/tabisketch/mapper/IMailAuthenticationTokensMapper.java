package com.tabisketch.mapper;

import com.tabisketch.bean.entity.MailAuthenticationToken;
import org.apache.ibatis.annotations.*;

import java.util.UUID;

@Mapper
public interface IMailAuthenticationTokensMapper {
    @Insert("INSERT INTO mail_authentication_tokens (user_id) VALUES (#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(final MailAuthenticationToken mailAuthenticationToken);

    @Select("SELECT * FROM mail_authentication_tokens WHERE token = #{token}")
    MailAuthenticationToken selectByToken(final UUID token);

    @Delete("DELETE FROM mail_authentication_tokens WHERE id = #{id}")
    int deleteById(final int id);
}
