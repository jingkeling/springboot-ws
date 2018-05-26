package com.kolin.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class WellController {



    @RequestMapping("index1")
    public String get(HttpServletResponse response) {

        return "index";

    }
}
