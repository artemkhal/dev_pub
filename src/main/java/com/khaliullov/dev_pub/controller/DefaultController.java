package com.khaliullov.dev_pub.controller;

/*
* для обычных запросов не через API (главная страница - /, в частности)
* */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String getIndex(){
        return "index";
    }
}
