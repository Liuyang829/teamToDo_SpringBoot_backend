package com.example.demo.domain;

public class Project_User {
    private Integer id;
    private Integer user_id;
    private Integer project_id;
    private String role;

    public Project_User(Integer id, Integer user_id, Integer project_id, String role) {
        this.id = id;
        this.user_id = user_id;
        this.project_id = project_id;
        this.role = role;
    }

    public Project_User(Integer user_id, Integer project_id, String role) {
        this.user_id = user_id;
        this.project_id = project_id;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getProject_id() {
        return project_id;
    }

    public void setProject_id(Integer project_id) {
        this.project_id = project_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
