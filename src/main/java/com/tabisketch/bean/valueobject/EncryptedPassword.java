package com.tabisketch.bean.valueobject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record EncryptedPassword(String value) {
    public EncryptedPassword(final String value) {
        final var passwordEncoder = new BCryptPasswordEncoder();
        this.value = passwordEncoder.encode(value);
    }
}
