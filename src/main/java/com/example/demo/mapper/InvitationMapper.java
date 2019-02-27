package com.example.demo.mapper;

import com.example.demo.domain.Invitation;

import java.util.List;
import java.util.Map;

public interface InvitationMapper {
    List<Map> getByProjectId(Integer project_id);
    Invitation getById(Integer id);
    List<Map> getByToUserId(Integer to_user_id);
    Invitation existCheck(Integer project_id,Integer to_user_id);
    void addInvitation(Invitation invitation);
    void updateStatus(Invitation invitation);
    void delInvitation(Integer id);
}
