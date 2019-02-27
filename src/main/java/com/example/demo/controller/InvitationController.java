package com.example.demo.controller;

import com.example.demo.domain.Invitation;
import com.example.demo.domain.Project_User;
import com.example.demo.domain.User;
import com.example.demo.service.InvitationService;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;
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
import java.util.Map;

@RestController
@RequestMapping("/invitations")
@CrossOrigin
public class InvitationController {
    @Autowired
    UserService userService;

    @Autowired
    InvitationService invitationService;

    @Autowired
    ProjectService projectService;

    @RequestMapping(method= RequestMethod.GET)
    public Result projectInvitation(int project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();


        List<Map> invitationList=invitationService.getByProjectId(project_id);

        return ResultFactory.buildSuccessResult(invitationList);
    }

    @RequestMapping(method= RequestMethod.POST)
    public Result addInvitation(int project_id,String email){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        User to_user=userService.getByEmail(email);

        if(to_user!=null){
            Project_User temp=new Project_User(user.getId(),project_id);
            String role=projectService.getRelation(temp);
            if(role!=null&&role.equals("creator")) {
                //是否已在project内
                Project_User t=new Project_User(to_user.getId(),project_id);
                String join_check=projectService.getRelation(t);
                if(join_check!=null){
                    return ResultFactory.buildFailResult("该成员已在project中");
                }
                //是否已发送请求
                Boolean exist_check= invitationService.existCheck(project_id,to_user.getId());
                if(!exist_check){
                    Date currentDate = new java.sql.Date(System.currentTimeMillis());
                    Invitation invitation=new Invitation(project_id,user.getId(),to_user.getId(),"待处理",currentDate);
                    invitationService.addInvitation(invitation);
                    return ResultFactory.buildSuccessResult(null);
                }
                return ResultFactory.buildFailResult("邀请已发送，等待对方确认");
            }
            return ResultFactory.buildFailResult("无法操作");
        }
        return ResultFactory.buildFailResult("查无此人");
    }

    @RequestMapping(method= RequestMethod.DELETE)
    public Result cancelInvitation(int project_id,int invitation_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User temp=new Project_User(user.getId(),project_id);
        String role=projectService.getRelation(temp);
        if(role!=null&&role.equals("creator")) {
            Invitation invitation=invitationService.getById(invitation_id);
            if(invitation!=null &&invitation.getProject_id()==project_id){
                invitationService.delInvitation(invitation_id);
                return ResultFactory.buildSuccessResult(null);
            }
            return ResultFactory.buildFailResult("无法操作");
        }
        return ResultFactory.buildFailResult("无法操作");
    }


    @RequestMapping(method= RequestMethod.GET,value = "/received")
    public Result receivedInvitation(){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        List<Map> invitationList=invitationService.getByToUserId(user.getId());

        return ResultFactory.buildSuccessResult(invitationList);
    }

    @RequestMapping(method= RequestMethod.POST,value = "/received")
    public Result acceptInvitation(int invitation_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Invitation invitation=invitationService.getById(invitation_id);
        if(invitation!=null&&user.getId()==invitation.getTo_user_id()&&invitation.getStatus().equals("待处理")){
            invitation.setStatus("同意");
            invitationService.updateStatus(invitation);
            Project_User project_user=new Project_User(user.getId(),invitation.getProject_id(),"member");
            projectService.addRelation(project_user);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.DELETE,value="/received")
    public  Result refuseInvitation(int invitation_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Invitation invitation=invitationService.getById(invitation_id);
        if(invitation!=null&&user.getId()==invitation.getTo_user_id()&&invitation.getStatus().equals("待处理")) {
            invitation.setStatus("拒绝");
            invitationService.updateStatus(invitation);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

}
