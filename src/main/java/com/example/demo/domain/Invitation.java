package com.example.demo.domain;
import java.sql.Date;


public class Invitation {
    Integer id;
    Integer project_id;
    Integer from_user_id;
    Integer to_user_id;
    String status;//同意、拒绝、待处理
    Date created;

    public Invitation(Integer id, Integer project_id, Integer from_user_id, Integer to_user_id, String status, Date created) {
        this.id = id;
        this.project_id = project_id;
        this.from_user_id = from_user_id;
        this.to_user_id = to_user_id;
        this.status = status;
        this.created = created;
    }

    public Invitation(Integer project_id, Integer from_user_id, Integer to_user_id, String status, Date created) {
        this.project_id = project_id;
        this.from_user_id = from_user_id;
        this.to_user_id = to_user_id;
        this.status = status;
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public Integer getFrom_user_id() {
        return from_user_id;
    }

    public void setFrom_user_id(Integer from_user_id) {
        this.from_user_id = from_user_id;
    }

    public Integer getTo_user_id() {
        return to_user_id;
    }

    public void setTo_user_id(Integer to_user_id) {
        this.to_user_id = to_user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Invitation{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", from_user_id=" + from_user_id +
                ", to_user_id=" + to_user_id +
                ", status='" + status + '\'' +
                ", created=" + created +
                '}';
    }
}
