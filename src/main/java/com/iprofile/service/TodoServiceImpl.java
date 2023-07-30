package com.iprofile.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.iprofile.model.Todo;
import com.iprofile.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getTodosByUser(String user) {
        return todoRepository.findByUserNameAndDoneOrderByPriorityAsc(user, false);
    }

    @Override
    public List<Todo> getAllTodosByUser(String user) {
        return todoRepository.findByUserNameOrderByPriorityAsc(user);
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void updateTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public void addTodo(String name, String desc, String priority, Date targetDate, boolean done) {
        todoRepository.save(new Todo(name, desc, priority, targetDate, false));
    }

    @Override
    public void deleteTodo(long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        todo.ifPresent(value -> todoRepository.delete(value));
    }

    @Override
    public void saveTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public List<Todo> fetchAllToDos() {
        return todoRepository.findAll();
    }

    @Override
    public List<Object[]> countTotalDescriptionByUsers() {
        return todoRepository.countTotalDescriptionByUsers();
    }
}