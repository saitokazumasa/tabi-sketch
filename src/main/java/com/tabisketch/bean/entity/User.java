package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public int id;
    public String mail;
    public String password;
    public Boolean isMailVerified;

    public static User generate(String mail, String password) {
        return new User(
                -1,
                mail,
                password,
                false
        );
    }
}
