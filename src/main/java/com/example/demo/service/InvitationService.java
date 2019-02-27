package com.example.demo.service;

import com.example.demo.domain.Invitation;

import java.util.List;
import java.util.Map;

public interface InvitationService {
    List<Map> getByProjectId(Integer project_id);
    List<Invitation> getByToUserId(Integer to_user_id);
    Boolean existCheck(Integer project_id,Integer to_user_id);
    Invitation getById(Integer id);
    void addInvitation(Invitation invitation);
//    void updateInvitation(Invitation invitation);
    void delInvitation(Integer id);
}
