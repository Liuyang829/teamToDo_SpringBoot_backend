package com.example.demo.service;

import com.example.demo.domain.Invitation;

import java.util.List;

public interface InvitationService {
    List<Invitation> getByProjectId(Integer project_id);
    List<Invitation> getByToUserId(Integer to_user_id);
    Invitation getById(Integer id);
    void addInvitation(Invitation invitation);
    void updateInvitation(Invitation invitation);
    void delInvitataion(Integer id);
}
