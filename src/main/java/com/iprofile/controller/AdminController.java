package com.iprofile.controller;

import com.iprofile.model.Diary;
import com.iprofile.model.ScheduleTodo;
import com.iprofile.model.Todo;
import com.iprofile.model.UserDetails;
import com.iprofile.service.DiaryService;
import com.iprofile.service.FileUploaderService;
import com.iprofile.service.ScheduleTodoService;
import com.iprofile.service.TodoService;
import com.iprofile.util.ExcelGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private TodoService todoService;

    @Autowired
    private ScheduleTodoService scheduleTodoService;

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private FileUploaderService fileUploaderService;

    @Autowired
    private WelcomeController welcomeController;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public String download(ModelMap model) {

        try {
            List<Object[]> objList = todoService.countTotalDescriptionByUsers();
            List<UserDetails> userDetailsList = new ArrayList<>();
            for (Object[] obj : objList) {
                UserDetails userDetails = new UserDetails();
                userDetails.setUsername(String.valueOf(obj[0]));
                userDetails.setMessageCount(String.valueOf(obj[1]));
                userDetailsList.add(userDetails);
            }
            log.debug("user details : " + userDetailsList);
            model.put("userdetails", userDetailsList);
            model.put("role", welcomeController.getLoggedinUserRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "download-data";
    }

    @GetMapping("/export-to-excel")
    public void exportIntoExcelFile(HttpServletResponse response, ModelMap model) throws IOException {
        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=todo_data.xlsx";
        response.setHeader(headerKey, headerValue);

        log.debug("export data to excel : " + headerValue);

        model.put("role", welcomeController.getLoggedinUserRole());

        List<Todo> listOfStudents = todoService.fetchAllToDos();
        List<ScheduleTodo> scheduleTodoList = scheduleTodoService.fetchScheduleTodo();
        List<Diary> diaryList = diaryService.fetchAllDiaryData();
        ExcelGenerator generator = new ExcelGenerator(listOfStudents, scheduleTodoList, diaryList);
        generator.generateExcelFile(response);
    }

    @GetMapping("/upload")
    public String upload(ModelMap model) {

        model.put("role", welcomeController.getLoggedinUserRole());
        return "upload-data";
    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, ModelMap model) {

        model.put("role", welcomeController.getLoggedinUserRole());
        fileUploaderService.uploadFile(file);

        log.debug("upload file : " + file.getName());

        redirectAttributes.addFlashAttribute("message",
                "You have successfully uploaded '" + file.getOriginalFilename() + "' !");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "redirect:/upload";
    }

    @GetMapping("/saveData")
    public String saveExcelData(Model model) {

        log.debug("save data in database");

        model.addAttribute("role", welcomeController.getLoggedinUserRole());
        List<Todo> excelDataAsList = fileUploaderService.getExcelDataAsList(0);
        List<ScheduleTodo> scheduleTodoList = fileUploaderService.getExcelDataAsList1(1);
        List<Diary> diaryList = fileUploaderService.getExcelDataAsList2(2);
        int noOfRecords = fileUploaderService.saveExcelData(excelDataAsList);
        int noOfRecords1 = fileUploaderService.saveExcelData1(scheduleTodoList);
        int noOfRecords2 = fileUploaderService.saveExcelData2(diaryList);
        model.addAttribute("noOfRecords", noOfRecords);
        model.addAttribute("noOfRecords1", noOfRecords1);
        model.addAttribute("noOfRecords2", noOfRecords2);
        return "upload-success";
    }
}
