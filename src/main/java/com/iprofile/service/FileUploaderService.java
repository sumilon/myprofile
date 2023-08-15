package com.iprofile.service;

import com.iprofile.model.Diary;
import com.iprofile.model.ScheduleTodo;
import com.iprofile.model.Todo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploaderService {

    List<Todo> getExcelDataAsList(int index);

    int saveExcelData(List<Todo> invoices);

    List<ScheduleTodo> getExcelDataAsList1(int index);

    int saveExcelData1(List<ScheduleTodo> invoices);

    List<Diary> getExcelDataAsList2(int index);

    int saveExcelData2(List<Diary> invoices);

    public void uploadFile(MultipartFile file);
}
