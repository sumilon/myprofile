package com.iprofile.service;

import com.iprofile.model.ScheduleTodo;
import com.iprofile.repository.ScheduleToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleTodoServiceImpl implements ScheduleTodoService {

    @Autowired
    private ScheduleToDoRepository scheduleToDoRepository;

    @Override
    public List<ScheduleTodo> getAllScheduleToDosByName(String username) {
        return scheduleToDoRepository.findByUserNameOrderByPriorityAsc(username);
    }

    @Override
    public List<ScheduleTodo> getScheduleToDosByJob(String job) {
        return scheduleToDoRepository.findByScheduleJob(job);
    }

    @Override
    public Optional<ScheduleTodo> getScheduleTodoById(long id) {
        return scheduleToDoRepository.findById(id);
    }

    @Override
    public void updateScheduleTodo(ScheduleTodo scheduleTodo) {
        scheduleToDoRepository.save(scheduleTodo);
    }

    @Override
    public void deleteScheduleTodo(long id) {
        Optional<ScheduleTodo> todo = scheduleToDoRepository.findById(id);
        todo.ifPresent(scheduleTodo -> scheduleToDoRepository.delete(scheduleTodo));
    }

    @Override
    public void saveScheduleTodo(ScheduleTodo scheduleTodo) {
        scheduleToDoRepository.save(scheduleTodo);
    }

    @Override
    public List<ScheduleTodo> fetchScheduleTodo() {
        return scheduleToDoRepository.findAll();
    }
}
