package com.tabisketch.bean.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class ExamplePasswordResetToken {
    public static PasswordResetToken generate() {
        final UUID token = UUID.fromString("d3a460f2-5df4-48b6-b9e1-a550e319512f");

        return new PasswordResetToken(
                1,
                token,
                1,
                LocalDateTime.now()
        );
    }
}
