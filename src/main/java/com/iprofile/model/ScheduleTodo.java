package com.iprofile.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "scheduletodos")
public class ScheduleTodo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;

    @Size(min = 10, message = "Enter at least 10 Characters...")
    private String description;

    private String priority;

    private String scheduleJob;

    private Boolean done = false;

    public ScheduleTodo() {
        super();
    }

    public ScheduleTodo(String user, String desc, String priority, String scheduleJob, Boolean done) {
        super();
        this.userName = user;
        this.description = desc;
        this.priority = priority;
        this.scheduleJob = scheduleJob;
        this.done = done;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getScheduleJob() {
        return scheduleJob;
    }

    public void setScheduleJob(String scheduleJob) {
        this.scheduleJob = scheduleJob;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "ScheduleTodo{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", description='" + description + '\'' +
                ", priority='" + priority + '\'' +
                ", scheduleJob='" + scheduleJob + '\'' +
                ", done=" + done +
                '}';
    }
}