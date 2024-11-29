package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailAuthenticationToken {
    private int id;
    private String token;
    private int userId;
    private LocalDateTime createdAt;

    public static MailAuthenticationToken generate(final String token, final int userId) {
        return new MailAuthenticationToken(
                -1,
                token,
                userId,
                LocalDateTime.now()
        );
    }
}
