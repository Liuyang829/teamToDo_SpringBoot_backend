package com.example.demo.service;

import com.example.demo.domain.Task;
import com.example.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskMapper taskMapper;

    @Override
    public List<Map> getByProjectId(Integer id) {
        return taskMapper.getByProjectId(id);
    }

    @Override
    public Task getById(Integer id) {
        return taskMapper.getById(id);
    }

    @Override
    public void addTask(Task task) {
        taskMapper.addTask(task);
    }

    @Override
    public void delTask(Integer task_id) {
        taskMapper.delTask(task_id);
    }

    @Override
    public void assignTask(Integer task_id, Integer owner_id) {
        taskMapper.assignTask(task_id,owner_id);
    }
}
