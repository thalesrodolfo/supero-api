package com.supero.desafio.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.supero.desafio.models.Task;
import com.supero.desafio.services.TaskListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskListController.class)
class TaskListControllerTest {
    @Autowired
    private TaskListController controller;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskListService taskListService;

    @Test
    public void test_get_all_tasks() throws Exception {
        Task task = new Task();
        task.setTitle("Task test");
        task.setDescription("Description");

        List<Task> listOfTasks = Arrays.asList(task);

        when(taskListService.getAllTasks()).thenReturn(listOfTasks);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tasks"))
                .andDo(print())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void test_save_new_task() throws Exception {
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Task test");
        task.setDescription("Description");
        task.setDone(false);

        when(taskListService.addNewTask(any(Task.class))).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/tasks")
                .content(new ObjectMapper().writeValueAsString(task))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Task test"));
    }

    @Test
    public void test_get_task_by_id() throws Exception {
        Task task = new Task();
        task.setId(10L);
        task.setTitle("Task test");
        task.setDescription("Description");
        task.setDone(false);

        when(taskListService.getOneTask(anyLong())).thenReturn(task);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/tasks/10"))
                .andExpect(jsonPath("$.title").value("Task test"))
                .andExpect(status().isOk());
    }


}