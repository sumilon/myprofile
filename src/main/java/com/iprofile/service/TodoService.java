package com.iprofile.service;

import com.iprofile.model.Todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoService {

    List<Todo> getTodosByUser(String user);

    List<Todo> getAllTodosByUser(String user);

    Optional<Todo> getTodoById(long id);

    void updateTodo(Todo todo);

    void addTodo(String name, String desc, String priority, Date targetDate, boolean isDone);

    void deleteTodo(long id);

    void saveTodo(Todo todo);

    List<Todo> fetchAllToDos();

    List<Object[]> countTotalDescriptionByUsers();
}