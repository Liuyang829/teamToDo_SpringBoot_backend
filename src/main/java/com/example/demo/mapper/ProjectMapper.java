package com.example.demo.mapper;

import com.example.demo.domain.Project;

import java.util.List;

public interface ProjectMapper {
    List<Project> getByOwnerId(Integer owner_id);
    void addProject(Project project);
}
