package com.iprofile.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String userName;

    @Column(length = 1000)
    private String notes;

    private Date createdDate;

    public Diary() {
        super();
    }

    public Diary(String userName, String notes, Date createdDate) {
        super();
        this.id = id;
        this.userName = userName;
        this.notes = notes;
        this.createdDate = createdDate;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
