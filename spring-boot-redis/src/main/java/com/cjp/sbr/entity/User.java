package com.cjp.sbr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String nickname;
    private String country;
    private String province;
    private String city;
}
