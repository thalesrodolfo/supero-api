package com.supero.desafio.services;

import com.supero.desafio.exceptions.TaskNotFoundException;
import com.supero.desafio.models.Task;
import com.supero.desafio.repositories.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TaskListServiceImpl implements TaskListService {
    @Autowired
    private TaskListRepository taskListRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskListRepository.findAll();
    }

    @Override
    public Task getOneTask(Long id) {
        return taskListRepository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @Override
    public Task addNewTask(Task newTask) {
        return taskListRepository.save(newTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskListRepository.deleteById(id);
    }

    @Override
    public Task toggleTask(Long id, Map<String, Object> updates) {
        Boolean done = (Boolean) updates.get("done");

        Optional<Task> dbTask = taskListRepository.findById(id);

        if (done == null)
            return dbTask.get();

        if (dbTask.isPresent()) {
            Task updatedTask = dbTask.get();

            updatedTask.setDone(done);

            return taskListRepository.save(updatedTask);
        } else {
            throw new TaskNotFoundException(id);
        }
    }

    @Override
    public Task updateTask(Long id, Task newTask) {
        Optional<Task> dbTask = taskListRepository.findById(id);

        if (dbTask.isPresent()) {
            Task updatedTask = dbTask.get();

            updatedTask.setTitle(newTask.getTitle());
            updatedTask.setDescription(newTask.getDescription());
            updatedTask.setDone(newTask.getDone());

            return taskListRepository.save(updatedTask);
        } else {
            throw new TaskNotFoundException(id);
        }

    }
}
