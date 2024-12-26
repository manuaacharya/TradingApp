package com.app.trade.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fName;
    private String lName;
    private String userName;
    private String dob;
    private String email;
    private String password;
    private String createdAt;

    public User(){

    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String fName, String lName, String userName, String dob, String email, String password) {
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public User(Long id, String fName, String lName, String userName, String dob, String email, String password) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.userName = userName;
        this.dob = dob;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public String getUserName() {
        return userName;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
