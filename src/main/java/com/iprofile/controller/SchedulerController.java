package com.iprofile.controller;

import com.iprofile.model.ScheduleTodo;
import com.iprofile.service.ScheduleTodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SchedulerController {

    @Autowired
    private ScheduleTodoService scheduleTodoService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-scheduletodos", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        String name = getLoggedInUserName();
        log.debug("list schedule todo data for : " + name);
        model.put("schuduletodos", scheduleTodoService.getAllScheduleToDosByName(name));
        return "list-scheduletodos";
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/add-scheduletodo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("add-scheduletodo", new ScheduleTodo());
        return "add-scheduletodo";
    }

    @RequestMapping(value = "/add-scheduletodo", method = RequestMethod.POST)
    public String addTodo(@Valid ScheduleTodo scheduleTodo, BindingResult result) {

        if (result.hasErrors()) {
            return "list-scheduletodos";
        }
        log.debug("added scheduletodo data : " + scheduleTodo.toString());
        scheduleTodo.setUserName(getLoggedInUserName());
        scheduleTodo.setDone(false);
        scheduleTodoService.saveScheduleTodo(scheduleTodo);
        return "redirect:/list-scheduletodos";
    }

    @RequestMapping(value = "/delete-scheduletodo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam long id) {
        log.debug("delete scheduletodo data for : " + id);
        scheduleTodoService.deleteScheduleTodo(id);
        return "redirect:/list-scheduletodos";
    }

    @RequestMapping(value = "/update-scheduletodo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
        log.debug("update scheduletodo data for : " + id);
        ScheduleTodo scheduleTodo = scheduleTodoService.getScheduleTodoById(id).get();
        model.put("add-scheduletodo", scheduleTodo);
        return "add-scheduletodo";
    }

    @RequestMapping(value = "/update-scheduletodo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid ScheduleTodo scheduleTodo, BindingResult result) {

        if (result.hasErrors()) {
            return "list-scheduletodos";
        }
        log.debug("updated scheduleTodo data : " + scheduleTodo.toString());
        scheduleTodo.setUserName(getLoggedInUserName());
        scheduleTodoService.updateScheduleTodo(scheduleTodo);
        return "redirect:/list-scheduletodos";
    }
}
