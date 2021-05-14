package com.designprojectteam.easytravelling.helper;

import com.designprojectteam.easytravelling.models.User;

public class Complains {
    private User user;
    private String complain;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getComplain() {
        return complain;
    }
    public void setComplain(String complain) {
        this.complain = complain;
    }
}

