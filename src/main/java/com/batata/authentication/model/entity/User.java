package com.batata.authentication.model.entity;

import javax.persistence.*;

/**
 * User table (ultra simple :D )
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "USERNAME", nullable = false, length = 30)
    private String user;
    @Column(name = "PASSWORD", length = 30)
    private String password;


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


