package com.tabisketch.mapper;

import com.tabisketch.bean.entity.MailAuthenticationToken;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMailAuthenticationTokensMapper {
    @Insert("")
    int insert(final MailAuthenticationToken mailAuthenticationToken);
}
