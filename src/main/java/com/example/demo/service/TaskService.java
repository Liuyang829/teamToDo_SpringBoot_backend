package com.example.demo.service;

import com.example.demo.domain.Task;

import java.util.List;

public interface TaskService {
    List<Task> getByProjectId(Integer id);
    void addTask(Task task);
}
