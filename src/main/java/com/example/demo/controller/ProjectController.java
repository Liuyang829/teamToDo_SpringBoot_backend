package com.example.demo.controller;

import com.example.demo.cors.Cors;
import com.example.demo.domain.Project;
import com.example.demo.domain.Project_User;
import com.example.demo.domain.Task;
import com.example.demo.domain.User;
import com.example.demo.service.ProjectService;
import com.example.demo.service.TaskService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
@CrossOrigin
public class ProjectController extends Cors {
    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @RequestMapping(method = RequestMethod.GET)
    public Result getProject() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();
        List<Map> myprojectList = projectService.getByUserId(user.getId());



        return ResultFactory.buildSuccessResult(myprojectList);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result addProject(String name, String description, String level, String state, Date start_time, Date end_time) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project project = new Project(name, description, level, state, start_time, end_time);

        project.setOwner_id(user.getId());
        Date currentDate = new java.sql.Date(System.currentTimeMillis());
        project.setCreated(currentDate);
        projectService.addProject(project);

        Project_User project_user = new Project_User(user.getId(), project.getId(), "creator");
        projectService.addRelation(project_user);
        return ResultFactory.buildSuccessResult(null);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result upProject(String name, String description, String level, String state, Date start_time, Date end_time,int project_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(temp);

        if (role != null && role.equals("creator")) {
            Project project = new Project(project_id,name, description, level, state, start_time, end_time);
            projectService.updateProject(project);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public Result delProject(int project_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project project = projectService.getById(project_id);
        if (project != null) {
            if (project.getOwner_id().equals(user.getId())) {
                projectService.deleteProject(project_id);
                return ResultFactory.buildSuccessResult(null);
            } else return ResultFactory.buildForbiddenResult(null);
        }
        return ResultFactory.buildFailResult("删除失败");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks")
    public Result getTask(int project_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Map project = projectService.getMapById(project_id);
        Project_User temp = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(temp);
        if (role != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("project", project);
            List<Map> taskList = taskService.getByProjectId(project_id);
            List<Map> memberList=projectService.getMemberByProjectId(project_id);
            map.put("tasks", taskList);
            map.put("members",memberList);
            return ResultFactory.buildSuccessResult(map);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks")
    public Result addTask(int project_id, String name, String description, String level, Date start_time, Date end_time) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(temp);

        if (role != null && role.equals("creator")) {
            Date currentDate = new java.sql.Date(System.currentTimeMillis());

            Task task = new Task(name, description, level, "待分配", start_time, end_time, currentDate, project_id, user.getId());
            taskService.addTask(task);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks")
    public Result delTask(int project_id, int task_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(temp);
        if (role != null && role.equals("creator")) {
            Task task = taskService.getById(task_id);
            if (task != null && task.getProject_id() == project_id) {
                taskService.delTask(task_id);
                return ResultFactory.buildSuccessResult(null);
            } else {
                return ResultFactory.buildFailResult("无法操作");
            }

        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public Result upTask(int project_id, int task_id,String name, String description, String level, String state, Date start_time, Date end_time) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(temp);

        if (role != null) {
            Task task = taskService.getById(task_id);
            if (task != null && task.getProject_id() == project_id) {
                if(role.equals("creator")){
                    task.setName(name);
                    task.setDescription(description);
                    task.setLevel(level);
                    task.setState(state);
                    task.setStart_time(start_time);
                    task.setEnd_time(end_time);
                    taskService.updateTask(task);
                }else if (task.getOwner_id()==user.getId()){
                    task.setState(state);
                    task.setDescription(description);
                    taskService.updateTask(task);
                }else{
                    return ResultFactory.buildFailResult("无法操作");
                }
                return ResultFactory.buildSuccessResult(null);
            } else {
                return ResultFactory.buildFailResult("无法操作");
            }

        }
        return ResultFactory.buildFailResult("无法操作");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/members")
    public Result getMembers(int project_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(temp);
        if (role != null) {
            return ResultFactory.buildSuccessResult(projectService.getMemberByProjectId(project_id));
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.POST, value = "/members")
    public Result assignTask(int task_id,int project_id,int owner_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User creator = new Project_User(user.getId(), project_id);
        String creator_role = projectService.getRelation(creator);
        if (creator_role != null && creator_role.equals("creator")) {
            Project_User owner = new Project_User(owner_id, project_id);
            String owner_role = projectService.getRelation(owner);
            if (owner_role == null) {
                return ResultFactory.buildFailResult("成员不属于该project");
            }
            Task task=taskService.getById(task_id);
            if(task!=null&task.getProject_id()==project_id){
                taskService.assignTask(task_id,owner_id);
                return ResultFactory.buildSuccessResult(null);
            }
            return ResultFactory.buildFailResult("无法操作");
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/members")
    public Result kickMember(int member_id,int project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(temp);

        if (role != null && role.equals("creator")&&member_id!=user.getId()) {
            projectService.kick(member_id,project_id);
            return ResultFactory.buildSuccessResult(null);
        }


        return ResultFactory.buildFailResult("无法操作");
    }






}
