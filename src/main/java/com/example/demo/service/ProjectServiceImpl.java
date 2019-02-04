package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public List<Project> getByOwnerId(Integer owner_id) {
        return projectMapper.getByOwnerId(owner_id);
    }

    @Override
    public void addProject(Project project) {
        projectMapper.addProject(project);
        return;
    }
}
