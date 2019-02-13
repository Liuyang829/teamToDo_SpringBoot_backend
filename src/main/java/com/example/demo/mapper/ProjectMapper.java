package com.example.demo.mapper;

import com.example.demo.domain.Project;
import com.example.demo.domain.Project_User;

import java.util.List;

public interface ProjectMapper {
    List<Project> getByOwnerId(Integer owner_id);
    Project getById(Integer id);
    Integer addProject(Project project);
    void addRelation(Project_User project_user);
    void deleteProject(Integer id);
}
