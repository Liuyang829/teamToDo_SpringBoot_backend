package com.example.demo.mapper;

import com.example.demo.domain.Task;

import java.util.List;

public interface TaskMapper {
    List<Task> getByProjectId(Integer id);
    Task getById(Integer id);
    void addTask(Task task);
    void delTask(Integer task_id);
}
