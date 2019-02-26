package com.example.demo.mapper;

import com.example.demo.domain.Invitation;

import java.util.List;

public interface InvitationMapper {
    List<Invitation> getByProjectId(Integer project_id);
    List<Invitation> getByToUserId(Integer to_user_id);
    void addInvitation(Invitation invitation);
    void updateInvitation(Invitation invitation);
    void delInvitataion(Integer id);
}
