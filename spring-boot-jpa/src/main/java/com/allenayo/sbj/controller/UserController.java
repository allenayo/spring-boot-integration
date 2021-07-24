package com.allenayo.sbj.controller;

import com.allenayo.sbj.domain.JsonResult;
import com.allenayo.sbj.domain.User;
import com.allenayo.sbj.service.UserService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult postUser(@RequestBody User user) {
        userService.save(user);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonResult putUser(@PathVariable("id") long id, @RequestBody User user) {
        User getUser = userService.findById(id);
        if (getUser == null) return JsonResult.get("1", "数据不存在");

        user.setId(getUser.getId());
        userService.update(user);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getUser(@PathVariable("id") long id) {
        return JsonResult.get("0", "操作成功", userService.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteUser(@PathVariable("id") long id) {
        User getUser = userService.findById(id);
        if (getUser == null) return JsonResult.get("1", "数据不存在");

        userService.deleteById(getUser.getId());
        return JsonResult.get("0", "操作成功");
    }
}