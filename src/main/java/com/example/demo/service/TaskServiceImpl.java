package com.example.demo.service;

import com.example.demo.domain.Task;
import com.example.demo.mapper.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    TaskMapper taskMapper;

    @Override
    public List<Task> getByProjectId(Integer id) {
        return taskMapper.getByProjectId(id);
    }

    @Override
    public void addTask(Task task) {

    }
}
