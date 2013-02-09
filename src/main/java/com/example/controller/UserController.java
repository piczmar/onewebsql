package com.example.controller;

import com.example.service.TaskService;
import com.example.service.UserService;
import com.generated.onewebsql.User;
import com.generated.onewebsql.UserTaskLikesCnt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private TaskService taskService;
    @Autowired
    UserService userService;

    @RequestMapping("/user/register.htm")
    public String register(Model model) {

        return "user/register";
    }

    @RequestMapping(value = "/user/doRegister.htm", method = RequestMethod.POST)
    public String doRegister(
            @RequestParam(value = "login", required = false) String login,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            Model model) {
        List<User> users = userService.getUsersByLogin(login);

        if (users.isEmpty()) {
            User user = new User();

            user.setLogin(login);
            user.setName(name);
            user.setSurname(surname);

            userService.save(user);
            return "user/login";
        }
        model.addAttribute("error", "User with this login name already exists");
        return "error";


    }

    @RequestMapping("/user/login.htm")
    public String login(Model model) {
        return "user/login";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/doLogin.htm")
    public String doLogin(@RequestParam(value = "login", required = false) String login,
                          @RequestParam(value = "password", required = false) String password,
                          HttpSession session,
                          Model model) {

        List<User> users = userService.getUsersByLogin(login);

        if (users.isEmpty()) {
            model.addAttribute("error", "Cannot login this user");
            return "error";
        } else if (users.size() > 1) {
            model.addAttribute("error", "Cannot login this userFound more than one user with the same login name, should never happen. Contact administrator.");
            return "error";
        }
        session.setAttribute("userId", users.get(0).getId());
        session.setAttribute("userLogin", users.get(0).getLogin());
        return "redirect:/index.htm";
    }

    @RequestMapping("/user/logout.htm")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/index.htm";
    }

    @RequestMapping("/index.htm")
    public ModelAndView root1(@RequestParam(required = false) String what, HttpSession session) {
        List<UserTaskLikesCnt> tasks = taskService.getTaskLikesCntLikeWhatAndUserId(what, null);
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("tasks", tasks);
        return mav;
    }
}
