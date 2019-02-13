package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.domain.Task;
import com.example.demo.domain.User;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TaskService;
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
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @RequestMapping(method= RequestMethod.GET)
    public Result getProject(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Project> projectList=projectService.getByOwnerId(user.getId());
        return ResultFactory.buildSuccessResult(projectList);
    }

    @RequestMapping(method= RequestMethod.POST)
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

    @RequestMapping(method= RequestMethod.DELETE)
    public Result delProject(int project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project project=projectService.getById(project_id);
        if(project!=null){
            if(project.getOwner_id().equals(user.getId())){
                projectService.deleteProject(project_id);
                return ResultFactory.buildSuccessResult(null);
            }else return ResultFactory.buildForbiddenResult(null);
        }
        return ResultFactory.buildFailResult("删除失败");
    }

    @RequestMapping(method= RequestMethod.GET,value = "/tasks")
    public Result getTask(Integer project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Task> taskList=taskService.getByProjectId(project_id);
        return null;

    }

    @RequestMapping(method= RequestMethod.POST,value = "/tasks")
    public Result addTask(String name, String description, String level, String state, Date start_time, Date  end_time){
        return null;
    }

    @RequestMapping(method= RequestMethod.DELETE,value = "/tasks")
    public Result delTask(int project_id){
        return null;
    }
}