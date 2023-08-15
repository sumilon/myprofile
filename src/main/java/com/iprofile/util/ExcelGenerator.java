package com.iprofile.util;

import com.iprofile.model.Diary;
import com.iprofile.model.ScheduleTodo;
import com.iprofile.model.Todo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class ExcelGenerator {

    private List<Todo> todoList;
    private List<ScheduleTodo> scheduleTodoList;
    List<Diary> diaryList;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelGenerator(List<Todo> todoList, List<ScheduleTodo> scheduleTodoList, List<Diary> diaryList) {
        this.todoList = todoList;
        this.scheduleTodoList = scheduleTodoList;
        this.diaryList = diaryList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeader(String element) {
        if(element.equals("TODO")) {
            sheet = workbook.createSheet("ToDos");
            Row row = sheet.createRow(0);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);
            createCell(row, 0, "ID", style);
            createCell(row, 1, "Description", style);
            createCell(row, 2, "Priority", style);
            createCell(row, 3, "Target Date", style);
            createCell(row, 4, "Is Done", style);
            createCell(row, 5, "Username", style);
        } else if(element.equals("SCHEDULER")) {
            sheet = workbook.createSheet("Scheduler");
            Row row = sheet.createRow(0);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);
            createCell(row, 0, "ID", style);
            createCell(row, 1, "Description", style);
            createCell(row, 2, "Priority", style);
            createCell(row, 3, "Schedule Job", style);
            createCell(row, 4, "Is Done", style);
            createCell(row, 5, "Username", style);
        } else if (element.equals("DIARY")) {
            sheet = workbook.createSheet("Diary");
            Row row = sheet.createRow(0);
            CellStyle style = workbook.createCellStyle();
            XSSFFont font = workbook.createFont();
            font.setBold(true);
            font.setFontHeight(16);
            style.setFont(font);
            createCell(row, 0, "ID", style);
            createCell(row, 1, "Created Date", style);
            createCell(row, 2, "Notes", style);
            createCell(row, 3, "Username", style);
        }
    }

    private void createCell(Row row, int columnCount, Object valueOfCell, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else if (valueOfCell instanceof Date) {
            cell.setCellValue((Date) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }

    private void write(String element) {
        int rowCount = 1;
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        if(element.equals("TODO")) {
            for (Todo record : todoList) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, record.getId(), style);
                createCell(row, columnCount++, record.getDescription(), style);
                createCell(row, columnCount++, record.getPriority(), style);
                createCell(row, columnCount++, record.getTargetDate(), style);
                createCell(row, columnCount++, record.getDone(), style);
                createCell(row, columnCount++, record.getUserName(), style);
            }
        } else if(element.equals("SCHEDULER")) {
            for (ScheduleTodo record : scheduleTodoList) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, record.getId(), style);
                createCell(row, columnCount++, record.getDescription(), style);
                createCell(row, columnCount++, record.getPriority(), style);
                createCell(row, columnCount++, record.getScheduleJob(), style);
                createCell(row, columnCount++, record.getDone(), style);
                createCell(row, columnCount++, record.getUserName(), style);
            }
        } else if (element.equals("DIARY")) {
            for (Diary record : diaryList) {
                Row row = sheet.createRow(rowCount++);
                int columnCount = 0;
                createCell(row, columnCount++, record.getId(), style);
                createCell(row, columnCount++, record.getCreatedDate(), style);
                createCell(row, columnCount++, record.getNotes(), style);
                createCell(row, columnCount++, record.getUserName(), style);
            }
        }
    }

    public void generateExcelFile(HttpServletResponse response) throws IOException {
        writeHeader("TODO");
        write("TODO");
        writeHeader("SCHEDULER");
        write("SCHEDULER");
        writeHeader("DIARY");
        write("DIARY");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}