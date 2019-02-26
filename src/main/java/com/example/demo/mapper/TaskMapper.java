package com.example.demo.mapper;

import com.example.demo.domain.Task;

import java.util.List;
import java.util.Map;

public interface TaskMapper {
    List<Map> getByProjectId(Integer id);
    Task getById(Integer id);
    void addTask(Task task);
    void delTask(Integer task_id);
}
