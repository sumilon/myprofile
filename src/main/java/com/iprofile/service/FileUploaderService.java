package com.iprofile.service;

import com.iprofile.model.Todo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileUploaderService {

    List<Todo> getExcelDataAsList();

    int saveExcelData(List<Todo> invoices);

    public void uploadFile(MultipartFile file);
}
