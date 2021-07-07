package com.cjp.sbx.util;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlUtil {

    public static String toXML(Object o) throws Exception {
        XmlMapper mapper = new XmlMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // 格式化
        return mapper.writeValueAsString(o);
    }

    public static <T> T toBean(String xmlString, Class<T> clazz) throws Exception {
        XmlMapper mapper = new XmlMapper();
        return mapper.readValue(xmlString, clazz);
    }
}