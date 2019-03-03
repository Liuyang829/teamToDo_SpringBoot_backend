package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.Project_User;
import com.example.demo.mapper.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public List<Map> getByOwnerId(Integer owner_id) {
        return projectMapper.getByOwnerId(owner_id);
    }


    @Override
    public Project getById(Integer id) {
        return projectMapper.getById(id);
    }

    @Override
    public Map getMapById(Integer id) {
        return projectMapper.getMapById(id);
    }

    @Override
    public List<Map> getByUserId(Integer user_id) {
        return projectMapper.getByUserId(user_id);
    }

    @Override
    public String getRelation(Project_User project_user) {
        return projectMapper.getRelation(project_user);
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

    @Override
    public List<Map> getMemberByProjectId(Integer project_id) {
        return projectMapper.getMemberByProjectId(project_id);
    }

    @Override
    public void updateProject(Project project) {
        projectMapper.updateProject(project);
    }
}
