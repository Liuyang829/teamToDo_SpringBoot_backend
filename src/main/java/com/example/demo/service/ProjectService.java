package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.Project_User;

import java.util.List;

public interface ProjectService {
    List<Project> getByOwnerId(Integer owner_id);
    Project getById(Integer id);
    Integer addProject(Project project);//返回主键
    void addRelation(Project_User project_user);
    void deleteProject(Integer id);
}
