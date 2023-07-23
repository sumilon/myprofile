package com.iprofile.repository;

import com.iprofile.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<Todo> findByUserNameAndDoneOrderByPriorityAsc(String user, boolean done);

    List<Todo> findByUserNameOrderByPriorityAsc(String user);
}
