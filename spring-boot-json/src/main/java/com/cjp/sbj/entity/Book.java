package com.cjp.sbj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    private String name;
    private String author;
    private String lang;
    private double price;
    private int totalPages;
    private List<Chapter> chapters;
}

