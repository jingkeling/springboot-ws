package com.kolin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class WellController {



    @RequestMapping("index1")
    public String get(HttpServletResponse response) {

        return "index";

    }
}
