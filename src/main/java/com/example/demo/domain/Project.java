package com.example.demo.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Project {
    private Integer id;
    private String name;
    private String description;
    private String level;
    private String state;
    private Integer owner_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date end_time;

    private Date created;

    public Project(Integer id, String name, String description, String level, String state, Integer owner_id, Date start_time, Date end_time, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.state = state;
        this.owner_id = owner_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.created = created;
    }

    public Project(Integer id, String name, String description, String level, String state, Date start_time, Date end_time, Date created) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.state = state;
        this.start_time = start_time;
        this.end_time = end_time;
        this.created = created;
    }

    public Project(Integer id,String name, String description, String level, String state, Date start_time, Date end_time) {
        this.id=id;
        this.name = name;
        this.description = description;
        this.level = level;
        this.state = state;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Project(String name, String description, String level, String state, Date start_time, Date end_time) {
        this.name = name;
        this.description = description;
        this.level = level;
        this.state = state;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", level='" + level + '\'' +
                ", state='" + state + '\'' +
                ", owner_id=" + owner_id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", created=" + created +
                '}';
    }
}
