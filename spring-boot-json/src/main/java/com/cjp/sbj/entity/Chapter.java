package com.cjp.sbj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    private Long id;
    private String title;
    private String content;
    private int words;
}
