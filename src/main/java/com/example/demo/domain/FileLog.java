package com.example.demo.domain;

import java.sql.Date;

public class FileLog {
    Integer id;
    Integer project_id;
    String original_name;
    String path;
    Integer creator_id;
    Boolean isDeleted;
    Date delete_time;
    Date create_time;

    public FileLog(Integer id, Integer project_id, String original_name, String path, Integer creator_id, Boolean isDeleted, Date delete_time,Date create_time) {
        this.id = id;
        this.project_id = project_id;
        this.original_name = original_name;
        this.path = path;
        this.creator_id = creator_id;
        this.isDeleted = isDeleted;
        this.delete_time = delete_time;
        this.create_time = create_time;
    }

    public FileLog(Integer id, Date delete_time) {
        this.id = id;
        this.delete_time = delete_time;
    }

    public FileLog(Integer project_id, String original_name, String path, Integer creator_id,Date create_time) {
        this.project_id = project_id;
        this.original_name = original_name;
        this.path = path;
        this.creator_id = creator_id;
        this.create_time = create_time;
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

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Integer creator_id) {
        this.creator_id = creator_id;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getDelete_time() {
        return delete_time;
    }

    public void setDelete_time(Date delete_time) {
        this.delete_time = delete_time;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
