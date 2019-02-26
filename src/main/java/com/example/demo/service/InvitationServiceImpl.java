package com.example.demo.service;

import com.example.demo.domain.Invitation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {
    @Override
    public List<Invitation> getByProjectId(Integer project_id) {
        return null;
    }

    @Override
    public List<Invitation> getByToUserId(Integer to_user_id) {
        return null;
    }

    @Override
    public Invitation getById(Integer id) {
        return null;
    }

    @Override
    public void addInvitation(Invitation invitation) {

    }

    @Override
    public void updateInvitation(Invitation invitation) {

    }

    @Override
    public void delInvitataion(Integer id) {

    }
}
