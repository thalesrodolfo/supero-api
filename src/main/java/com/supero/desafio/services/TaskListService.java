package com.supero.desafio.services;

import com.supero.desafio.models.Task;

import java.util.List;
import java.util.Map;

public interface TaskListService {
    List<Task> getAllTasks();
    Task getOneTask(Long id);
    Task addNewTask(Task newTask);
    void deleteTask(Long id);

    Task toggleTask(Long id, Map<String, Object> updates);

    Task updateTask(Long id, Task newTask);
}
