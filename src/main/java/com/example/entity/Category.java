package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Category {

    private Integer id;
    private  String name;

    public Category(String name) {
        this.name = name;
    }
}
