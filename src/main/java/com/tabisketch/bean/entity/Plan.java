package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    public int id;
    public UUID uuid;
    public String title;
    public int userId;
    public boolean isEditable;
    public boolean isPublic;
}
