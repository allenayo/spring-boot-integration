package com.cjp.sbx.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY) // 字段为空不参与转换
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Long id;
    @JacksonXmlCData // 文本内容加上CDATA块
    private String name;
    @JacksonXmlCData
    private String author;
    @JacksonXmlCData
    private String lang;
    private double price;
    private int totalPages;
    @JacksonXmlElementWrapper(localName = "chapters") // 元素名称
    @JacksonXmlProperty(localName = "chapter") // 子元素名称
    private List<Chapter> chapters;
}

