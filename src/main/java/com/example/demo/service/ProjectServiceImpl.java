package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.Project_User;
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
    public Project getById(Integer id) {
        return projectMapper.getById(id);
    }

    @Override
    public Integer addProject(Project project) {
        return projectMapper.addProject(project);
    }

    @Override
    public void addRelation(Project_User project_user) {
        projectMapper.addRelation(project_user);
    }

    @Override
    public void deleteProject(Integer id) {
        projectMapper.deleteProject(id);
    }
}
