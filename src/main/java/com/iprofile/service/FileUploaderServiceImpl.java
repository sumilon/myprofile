package com.iprofile.service;

import com.iprofile.model.Todo;
import com.iprofile.repository.TodoRepository;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class FileUploaderServiceImpl implements FileUploaderService {

    @Value("${app.upload.file}")
    public String EXCEL_FILE_PATH;

    @Value("${app.upload.dir}")
    public String uploadDir;

    @Autowired
    TodoRepository repo;

    Workbook workbook;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Todo> getExcelDataAsList() {
        List<String> list = new ArrayList<String>();

        // Create a DataFormatter to format and get each cell's value as String
        DataFormatter dataFormatter = new DataFormatter();

        // Create the Workbook
        try {
            workbook = WorkbookFactory.create(new File(EXCEL_FILE_PATH));
        } catch (EncryptedDocumentException | IOException e) {
            e.printStackTrace();
        }

        // Retrieving the number of sheets in the Workbook
        log.debug("-------Workbook has '" + workbook.getNumberOfSheets() + "' Sheets-----");

        // Getting the Sheet at index zero
        Sheet sheet = workbook.getSheetAt(0);

        // Getting number of columns in the Sheet
        int noOfColumns = sheet.getRow(0).getLastCellNum();
        log.debug("-------Sheet has '" + noOfColumns + "' columns------");

        // Using for-each loop to iterate over the rows and columns
        for (Row row : sheet) {
            for (Cell cell : row) {
                String cellValue = dataFormatter.formatCellValue(cell);
                if (!cellValue.isEmpty())
                    list.add(cellValue);
                else
                    break;
            }
        }

        // filling excel data and creating list as List<Invoice>
        List<Todo> invList = createList(list, noOfColumns);

        // Closing the workbook
        try {
            workbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return invList;
    }

    private List<Todo> createList(List<String> excelData, int noOfColumns) {

        ArrayList<Todo> invList = new ArrayList<Todo>();

        int i = noOfColumns;
        do {
            Todo inv = new Todo();
            inv.setId(Long.parseLong(excelData.get(i)));
            inv.setDescription(String.valueOf(excelData.get(i + 1)));
            inv.setPriority(String.valueOf(excelData.get(i + 2)));
            try {
                inv.setTargetDate(new SimpleDateFormat("MM/dd/yyyy").parse(excelData.get(i + 3)));
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
            inv.setDone(Boolean.valueOf(excelData.get(i + 4)));
            inv.setUserName(String.valueOf(excelData.get(i + 5)));

            invList.add(inv);
            i = i + (noOfColumns);

        } while (i < excelData.size());
        return invList;
    }


    @Override
    public int saveExcelData(List<Todo> todoList) {
        todoList = repo.saveAll(todoList);
        return todoList.size();
    }

    @Override
    public void uploadFile(MultipartFile file) {
        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
    }
}
