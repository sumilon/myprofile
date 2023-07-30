package com.iprofile.repository;

import com.iprofile.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findByUserNameAndDoneOrderByPriorityAsc(String user, boolean done);

    List<Todo> findByUserNameOrderByPriorityAsc(String user);

    @Query("SELECT userName, COUNT(description) FROM Todo GROUP BY userName")
    List<Object[]> countTotalDescriptionByUsers();
}
