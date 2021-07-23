package com.cjp.sbv.controller;

import com.cjp.sbv.entity.JsonResult;
import com.cjp.sbv.entity.Update;
import com.cjp.sbv.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("user")
@RestController
public class UserController {

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult postUser(@RequestBody @Valid User user) {
        return JsonResult.get("0", "操作成功", user);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult putUser(@RequestBody @Validated(Update.class) User user) {
        return JsonResult.get("0", "操作成功", user);
    }
}
