package com.iprofile.controller;

import com.iprofile.model.Diary;
import com.iprofile.service.DiaryService;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/list-diary", method = RequestMethod.GET)
    public String showDiary() {
        log.debug("Go to Diary List Page");
        return "list-diary";
    }

    @GetMapping(value = "/getDiaryData")
    @ResponseBody
    public Map<String, List<Diary>> getAllDiaryDataJSON() {
        log.debug("fetch diary data for user : " + getLoggedInUserName());
        Map<String, List<Diary>> diaryData = new HashMap<>();
        diaryData.put("data", diaryService.getDiaryByUserName(getLoggedInUserName()));
        return diaryData;
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/add-diary", method = RequestMethod.GET)
    public String showAddDiaryPage(ModelMap model) {
        model.addAttribute("add-diary", new Diary());
        return "add-diary";
    }

    @RequestMapping(value = "/add-diary", method = RequestMethod.POST)
    public String addDiary(@Valid Diary diary, BindingResult result) {

        if (result.hasErrors()) {
            return "list-diary";
        }
        log.debug("added diary data : " + diary.getUserName());
        diary.setUserName(getLoggedInUserName());
        diaryService.saveDiary(diary);
        return "redirect:/list-diary";
    }

    @RequestMapping(value = "/delete-diary", method = RequestMethod.GET)
    public String deleteDiary(@RequestParam long id) {
        log.debug("delete diary data for : " + id);
        diaryService.deleteDiary(id);
        return "redirect:/list-diary";
    }

    @RequestMapping(value = "/update-diary", method = RequestMethod.GET)
    public String showUpdateDiaryPage(@RequestParam long id, ModelMap model) {
        log.debug("update diary data for : " + id);
        Diary diary = diaryService.getDiaryById(id).get();
        model.put("add-diary", diary);
        return "add-diary";
    }

    @RequestMapping(value = "/update-diary", method = RequestMethod.POST)
    public String updateDiary(@Valid Diary diary, BindingResult result) {

        if (result.hasErrors()) {
            return "list-diary";
        }
        log.debug("updated diary data : " + diary.getUserName());
        diary.setUserName(getLoggedInUserName());
        diaryService.updateDiary(diary);
        return "redirect:/list-diary";
    }
}
