package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @RequestMapping("/index.htm")
    public ModelAndView root1(@RequestParam(required = false) String what, HttpSession session) {
        ModelAndView mav = new ModelAndView("index");
        return mav;
    }
}
