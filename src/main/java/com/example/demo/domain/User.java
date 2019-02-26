package com.example.demo.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class User {
    private String name;
    private Integer id;
    private String email;
    private String role;
    private String password;

    public User(String email, String password,String name) {
        this.name = name;
        this.email = email;
        this.role = "user";
        this.password = password;
    }

    public User(Integer id,String name, String email, String password, String role) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.role = role;
        this.password = password;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
