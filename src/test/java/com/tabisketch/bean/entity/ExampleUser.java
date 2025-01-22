package com.tabisketch.bean.entity;

public class ExampleUser {
    public static User generate() {
        return new User(
                1,
                "sample@example.com",
                "$2a$10$FFbAunp0hfeWTCune.XqwO/P/61fqWlbruV/8wqzrhM3Pw0VuXxpa",
                false
        );
    }
}
