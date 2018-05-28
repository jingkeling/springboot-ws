package com.kolin.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class WellController {



    @RequestMapping("index")
    public String get(HttpServletResponse response) {
        log.info("index方法的日志{}", ":你好");
        System.out.println("你好");
        return "index";

    }
}
