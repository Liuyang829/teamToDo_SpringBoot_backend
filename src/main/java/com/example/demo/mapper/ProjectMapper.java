package com.example.demo.mapper;

import com.example.demo.domain.Project;
import com.example.demo.domain.Project_User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectMapper {
    List<Map> getByOwnerId(Integer owner_id);
    Project getById(Integer id);
    Map getMapById(Integer id);
    String getRelation(Project_User project_user);
    Integer addProject(Project project);
    void addRelation(Project_User project_user);
    void deleteProject(Integer id);
    List<Map> getMemberByProjectId(Integer project_id);
}
