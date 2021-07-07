package com.cjp.sbx.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_EMPTY) // 字段为空不参与转换
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chapter {
    private Long id;
    @JacksonXmlCData // 文本内容加上CDATA块
    private String title;
    @JacksonXmlCData // 文本内容加上CDATA块
    private String content;
    private int words;
}
