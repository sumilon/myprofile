package com.iprofile.taskconfig;

import com.iprofile.model.ScheduleTodo;
import com.iprofile.service.ScheduleTodoService;
import com.iprofile.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private ScheduleTodoService scheduleTodoService;

    @Autowired
    private TodoService todoService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "MM/dd/yyyy HH:mm:ss");

    @Scheduled(cron = "0 30 5 * * MON")
    public void performTaskUsingCronWeekly() {

        saveDataInToDos("WEEKLY", "for date " + LocalDate.now().toString());
        log.debug("Regular weekly task performed using Cron at "
                + dateFormat.format(new Date()));

    }

    @Scheduled(cron = "0 30 5 1 * ?")
    public void performTaskUsingCronMonthly() {

        saveDataInToDos("MONTHLY", "for month " + LocalDate.now().getMonth().toString());
        log.debug("Regular monthly task performed using Cron at "
                + dateFormat.format(new Date()));

    }

    private void saveDataInToDos(String job, String value) {

        List<ScheduleTodo> scheduleTodoList = scheduleTodoService.getScheduleToDosByJob(job);
        for (ScheduleTodo scheduleTodo : scheduleTodoList) {
            todoService.addTodo(scheduleTodo.getUserName(), scheduleTodo.getDescription() + " " + value,
                    scheduleTodo.getPriority(), new Date(), scheduleTodo.getDone());
            log.debug("Save schedule data for : " + scheduleTodo.toString());
        }
    }
}
