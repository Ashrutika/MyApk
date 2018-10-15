package com.example.shrut.myapk;

/**
 * Created by root1 on 15/10/18.
 */

public class AllUsers {

    private String id;

    private ProfileList profileList;

    public AllUsers(String id, ProfileList profileList) {
        this.id = id;
        this.profileList = profileList;
    }

    public AllUsers() {
    }

    public String getId() {
        return id;
    }

    public ProfileList getProfileList() {
        return profileList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setProfileList(ProfileList profileList) {
        this.profileList = profileList;
    }
}
