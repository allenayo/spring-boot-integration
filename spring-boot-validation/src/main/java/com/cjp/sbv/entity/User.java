package com.cjp.sbv.entity;

import com.cjp.sbv.annotation.CheckCase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotNull(groups = Update.class)
    private Long id;
    @CheckCase(value = CaseMode.LOWER, message = "必须是小写")
    @NotEmpty
    private String username;
    private String nickname;
    @NotNull
    @Length(min = 3, max = 12)
    private String password;
    private String phone;
    @Email
    private String email;
}
