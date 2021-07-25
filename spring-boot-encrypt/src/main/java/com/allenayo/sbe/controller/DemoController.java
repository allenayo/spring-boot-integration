package com.allenayo.sbe.controller;

import com.allenayo.sbe.config.GlobalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    private GlobalConfig globalConfig;

    @RequestMapping("author")
    public String getAuthor() {
        return globalConfig.getAuthor();
    }
}
