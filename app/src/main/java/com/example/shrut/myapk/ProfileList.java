package com.example.shrut.myapk;

/**
 * Created by root1 on 2/10/18.
 */

public class ProfileList {
    private String imageUrl;
    private String name;

    public ProfileList(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
       // this.status = status;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

//    public String getStatus() {
//        return status;
//    }


   // private String status;
}
