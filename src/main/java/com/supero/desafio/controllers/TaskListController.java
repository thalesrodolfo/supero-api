package com.supero.desafio.controllers;

import com.supero.desafio.models.Task;
import com.supero.desafio.services.TaskListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class TaskListController {

    private TaskListService taskListService;

    public TaskListController(TaskListService taskListService) {
        this.taskListService = taskListService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskListService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getOneTask(@PathVariable Long id) {
        return new ResponseEntity<>(taskListService.getOneTask(id), HttpStatus.OK);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskListService.addNewTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task) {
        return new ResponseEntity<>(taskListService.updateTask(id, task), HttpStatus.OK);
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> toggleTask(@RequestBody Map<String, Object> updates,
                                           @PathVariable("id") Long id) {
        return new ResponseEntity<>(taskListService.toggleTask(id, updates), HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long id) {
        taskListService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
