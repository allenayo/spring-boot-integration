package com.cjp.sbv.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class JsonResult implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;
    private Object data;

    public static JsonResult get(String code, String msg, Object data) {
        return new JsonResult(code, msg, data);
    }

}