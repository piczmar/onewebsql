package com.example.controller;

import com.generated.onewebsql.LikesDAO;
import com.generated.onewebsql.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LikeController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LikesDAO likesDAO;


    @RequestMapping("/like/")
    public String userRoot1(Model model) {
        return "user/login";
    }

    @RequestMapping("/like/index.htm")
    public String userRoot2(Model model) {
        return "user/login";
    }

}
