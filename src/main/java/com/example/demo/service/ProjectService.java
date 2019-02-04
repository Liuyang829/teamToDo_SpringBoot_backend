package com.example.demo.service;

import com.example.demo.domain.Project;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjectService {
    List<Project> getByOwnerId(Integer owner_id);
    void addProject(Project project);
}
