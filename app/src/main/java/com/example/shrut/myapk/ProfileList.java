package com.example.shrut.myapk;

/**
 * Created by root1 on 2/10/18.
 */

public class ProfileList {

    private String email;
    private String userName;
    private String userId;
    private String contact;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserId() {

        return userId;
    }

    public String getContact() {
        return contact;
    }

    public ProfileList(String userId, String email, String userName, String contact) {
        this.email = email;
        this.userName = userName;
        this.userId = userId;
        this.contact = contact;
    }


    public ProfileList() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
