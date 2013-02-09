package com.example.controller;

import com.example.service.TaskService;
import com.example.service.UserService;
import com.example.util.SqlDateEditor;
import com.example.validator.TaskValidator;
import com.generated.tasks.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @Autowired
    private TaskValidator validator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new SqlDateEditor(true));
    }

    @RequestMapping("/task/searchTask.htm")
    public ModelAndView searchTasks(@RequestParam(required = false, defaultValue = "") String what, HttpSession session) {
        ModelAndView mav = new ModelAndView();
        if ("".equals(what)) {
            mav.setViewName("redirect:viewUserTasks.htm");
        } else {
            mav.setViewName("task/showTasks");
        }
        List<Task> tasks = null;
        Long userId = (Long) session.getAttribute("userId");
        if (userId != null) {
            tasks = taskService.getTasksLikeWhatAndUserId(what, userId);
        } else {
            tasks = taskService.getTasksLikeWhatAndUserId(what, null);
        }

        mav.addObject("tasks", tasks);
        return mav;
    }

    @RequestMapping("/task/viewUserTasks.htm")
    public ModelAndView getAllTasks(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        ModelAndView mav = new ModelAndView("task/showTasks");

        List<Task> tasks = taskService.getTasksLikeWhatAndUserId(null, userId);
        mav.addObject("tasks", tasks);
        return mav;
    }

    @RequestMapping(value = "/task/saveTask.htm", method = RequestMethod.GET)
    public ModelAndView newTask() {
        ModelAndView mav = new ModelAndView("task/newTask");
        Task task = new Task();
        mav.getModelMap().put("newTask", task);
        return mav;
    }

    @RequestMapping(value = "/task/saveTask.htm", method = RequestMethod.POST)
    public String create(@ModelAttribute("newTask") Task task, BindingResult result, HttpSession session, SessionStatus status) {
        validator.validate(task, result);
        if (result.hasErrors()) {
            return "task/newTask";
        }
        task.setOwnedBy((Long) session.getAttribute("userId"));
        taskService.save(task);
        status.setComplete();
        return "redirect:viewUserTasks.htm";
    }

    @RequestMapping(value = "task/updateTask", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("task/editTask");
        Task task = taskService.getTasksById(id);
        mav.addObject("editTask", task);
        return mav;
    }

    @RequestMapping(value = "task/updateTask", method = RequestMethod.POST)
    public String update(@ModelAttribute("editTask") Task task, BindingResult result, SessionStatus status) {
        validator.validate(task, result);
        if (result.hasErrors()) {
            return "task/editTask";
        }
        taskService.save(task);
        status.setComplete();
        return "redirect:viewUserTasks.htm";
    }

    @RequestMapping("/task/deleteTask.htm")
    public ModelAndView delete(@RequestParam("id") Long id) {
        ModelAndView mav = new ModelAndView("redirect:viewUserTasks.htm");
        taskService.delete(id);
        return mav;
    }

    @RequestMapping("/task/like.htm")
    public ModelAndView like(@RequestParam("id") Long taskId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        ModelAndView mav = new ModelAndView("redirect:/index.htm");
        taskService.like(taskId, userId);
        return mav;
    }

    @RequestMapping("/task/")
    public String userRoot1(Model model) {
        return "task/new";
    }

    @RequestMapping("/task/index.htm")
    public String userRoot2(Model model) {
        return "task/new";
    }


}
