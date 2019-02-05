package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.service.ProjectService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(method= RequestMethod.GET,value = "/project")
    public Result getProject(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Project> projectList=projectService.getByOwnerId(user.getId());
        return ResultFactory.buildSuccessResult(projectList);
    }

    @RequestMapping(method= RequestMethod.POST,value = "/project")
    public Result addProject(String name, String description, String level, String state, Date start_time, Date  end_time){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project project=new Project(name,description,level,state,start_time,end_time);

        project.setOwner_id(user.getId());
        Date currentDate = new java.sql.Date(System.currentTimeMillis());
        project.setCreated(currentDate);
        projectService.addProject(project);
        return ResultFactory.buildSuccessResult(null);
    }
}
