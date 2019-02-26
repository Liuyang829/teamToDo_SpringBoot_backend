package com.example.demo.controller;

import com.example.demo.domain.Invitation;
import com.example.demo.domain.Project;
import com.example.demo.domain.Project_User;
import com.example.demo.domain.User;
import com.example.demo.service.InvitationService;
import com.example.demo.service.ProjectService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/invitations")
@CrossOrigin
public class InvitationController {
    @Autowired
    InvitationService invitationService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(method= RequestMethod.GET)
    public Result projectInvitation(int project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        List<Invitation> invitationList=invitationService.getByProjectId(project_id);

        return ResultFactory.buildSuccessResult(invitationList);
    }

    @RequestMapping(method= RequestMethod.POST)
    public Result addInvitation(int project_id,int from_user_id,int to_user_id, String status){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp=new Project_User(user.getId(),project_id);
        String role=projectService.getRelation(temp);
        if(role!=null ) {
            Date currentDate = new java.sql.Date(System.currentTimeMillis());
            Invitation invitation=new Invitation(project_id,from_user_id,to_user_id,status,currentDate);
            invitationService.addInvitation(invitation);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public Result cancelInvitation(int project_id,int invitation_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp=new Project_User(user.getId(),project_id);
        String role=projectService.getRelation(temp);
        if(role!=null) {
            Invitation invitation=invitationService.getById(invitation_id);
            if(invitation!=null){
                invitationService.delInvitataion(invitation_id);
                return ResultFactory.buildSuccessResult(null);
            }
            return ResultFactory.buildFailResult("无法操作");

        }
        return ResultFactory.buildFailResult("无法操作");
    }


    @RequestMapping(method= RequestMethod.GET,value = "/received")
    public Result receivedInvitation(int to_user_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        List<Invitation> invitationList=invitationService.getByToUserId(to_user_id);

        return ResultFactory.buildSuccessResult(invitationList);
    }

    @RequestMapping(method= RequestMethod.POST,value = "/received")
    public Result acceptInvitation(int invitation_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Invitation invitation=invitationService.getById(invitation_id);
        if(invitation!=null&&user.getId()==invitation.getTo_user_id()){
            //...
            //...
            Project_User project_user=new Project_User(user.getId(),invitation.getProject_id(),"member");
            projectService.addRelation(project_user);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }



}
