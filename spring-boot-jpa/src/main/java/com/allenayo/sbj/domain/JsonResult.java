package com.allenayo.sbj.domain;

import lombok.Data;

import java.io.Serializable;

@Data(staticConstructor = "of")
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private Object data;

    public static JsonResult get(String code, String msg, Object data) {
        JsonResult result = of();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static JsonResult get(String code, String msg) {
        return get(code, msg, null);
    }
}