package com.cjp.sbx.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JacksonXmlRootElement(localName = "xml") // 根元素名称
@JsonInclude(JsonInclude.Include.NON_EMPTY) // 字段为空不参与转换
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @JacksonXmlCData // 文本内容增加CDATA块
    @JacksonXmlProperty(localName = "ToUserName") // 元素名称
    private String toUserName;
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "FromUserName")
    private String fromUserName;
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "CreateTime")
    private long createTime;
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "MsgType")
    private String msgType;
    @JacksonXmlCData
    @JacksonXmlProperty(localName = "Content")
    private String content;
    @JacksonXmlProperty(localName = "MsgId")
    private long msgId;
}