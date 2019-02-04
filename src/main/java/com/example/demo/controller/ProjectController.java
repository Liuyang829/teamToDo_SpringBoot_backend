package com.example.demo.controller;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
import com.example.demo.service.ProjectService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @RequestMapping(method= RequestMethod.GET,value = "/project")
    public Result project(){
        Subject subject = SecurityUtils.getSubject();

        //根据cookie获取信息
        User user = (User) subject.getPrincipal();

        List<Project> projectList=projectService.getByOwnerId(user.getId());


        return ResultFactory.buildSuccessResult(projectList);
    }
}
