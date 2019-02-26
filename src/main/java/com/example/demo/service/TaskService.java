package com.example.demo.service;

import com.example.demo.domain.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<Map> getByProjectId(Integer id);
    Task getById(Integer id);
    void addTask(Task task);
    void delTask(Integer task_id);
}
