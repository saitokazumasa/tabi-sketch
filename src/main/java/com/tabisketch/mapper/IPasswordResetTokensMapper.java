//package com.tabisketch.mapper;
//
//import com.tabisketch.bean.entity.PasswordResetToken;
//import org.apache.ibatis.annotations.*;
//
//import java.util.UUID;
//
//@Mapper
//public interface IPasswordResetTokensMapper {
//    @Insert("INSERT INTO password_reset_tokens (user_id) VALUES (#{userId})")
//    @Options(useGeneratedKeys = true, keyProperty = "id,token")
//    int insert(final PasswordResetToken passwordResetToken);
//
//    @Select("SELECT * FROM password_reset_tokens WHERE token = #{token}")
//    PasswordResetToken selectByToken(final UUID token);
//
//    @Delete("DELETE FROM password_reset_tokens WHERE id = #{id}")
//    int deleteById(final int id);
//}
