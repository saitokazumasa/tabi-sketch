package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailAuthenticationToken {
    private int id;
    private UUID token;
    private int userId;
    private LocalDateTime createdAt;

    public static MailAuthenticationToken generate(final int userId) {
        return new MailAuthenticationToken(
                -1,
                UUID.randomUUID(),
                userId,
                LocalDateTime.now()
        );
    }
}
