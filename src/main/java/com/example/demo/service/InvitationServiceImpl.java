package com.example.demo.service;

import com.example.demo.domain.Invitation;
import com.example.demo.mapper.InvitationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Autowired
    InvitationMapper invitationMapper;

    @Override
    public List<Map> getByProjectId(Integer project_id) {
        return invitationMapper.getByProjectId(project_id);
    }

    @Override
    public List<Invitation> getByToUserId(Integer to_user_id) {
        return null;
    }

    @Override
    public Boolean existCheck(Integer project_id,Integer to_user_id) {
        return invitationMapper.existCheck(project_id,to_user_id)==null?false:true;
    }

    @Override
    public Invitation getById(Integer id) {
        return invitationMapper.getById(id);
    }

    @Override
    public void addInvitation(Invitation invitation) {
        invitationMapper.addInvitation(invitation);
    }

//    @Override
//    public void updateInvitation(Invitation invitation) {
//
//    }

    @Override
    public void delInvitation(Integer id) {
        invitationMapper.delInvitation(id);
    }
}
