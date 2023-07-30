package com.iprofile.repository;

import com.iprofile.model.ScheduleTodo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleToDoRepository extends JpaRepository<ScheduleTodo, Long> {

    List<ScheduleTodo> findByScheduleJob(String scheduleJob);

    List<ScheduleTodo> findByUserNameOrderByPriorityAsc(String user);
}
