package com.tabisketch.bean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plan {
    private int id;
    private UUID uuid;
    private String title;
    private int userId;
    private boolean isEditable;
    private boolean isPublic;
}
