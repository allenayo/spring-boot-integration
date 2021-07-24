package com.allenayo.sbj.controller;

import com.allenayo.sbj.domain.JsonResult;
import com.allenayo.sbj.domain.School;
import com.allenayo.sbj.service.SchoolService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("school")
@RestController
public class SchoolController {

    private final SchoolService schoolService;

    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public JsonResult postSchool(@RequestBody School school) {
        schoolService.save(school);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public JsonResult putSchool(@PathVariable("id") long id, @RequestBody School school) {
        School getSchool = schoolService.findById(id);
        if (getSchool == null) return JsonResult.get("1", "数据不存在");

        school.setId(getSchool.getId());
        schoolService.update(school);
        return JsonResult.get("0", "操作成功");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getSchool(@PathVariable("id") long id) {
        return JsonResult.get("0", "操作成功", schoolService.findById(id));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public JsonResult deleteSchool(@PathVariable("id") long id) {
        School getSchool = schoolService.findById(id);
        if (getSchool == null) return JsonResult.get("1", "数据不存在");

        schoolService.deleteById(getSchool.getId());
        return JsonResult.get("0", "操作成功");
    }
}