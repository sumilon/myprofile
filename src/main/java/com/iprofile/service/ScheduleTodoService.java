package com.iprofile.service;

import com.iprofile.model.ScheduleTodo;

import java.util.List;
import java.util.Optional;

public interface ScheduleTodoService {

    List<ScheduleTodo> getAllScheduleToDosByName(String username);

    List<ScheduleTodo> getScheduleToDosByJob(String job);

    Optional<ScheduleTodo> getScheduleTodoById(long id);

    void updateScheduleTodo(ScheduleTodo scheduleTodo);

    void deleteScheduleTodo(long id);

    void saveScheduleTodo(ScheduleTodo scheduleTodo);

    List<ScheduleTodo> fetchScheduleTodo();
}
