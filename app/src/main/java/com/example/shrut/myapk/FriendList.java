package com.example.shrut.myapk;

/**
 * Created by root1 on 10/10/18.
 */

public class FriendList {

    private String imageUrl;
    private String name;
//    private String btnaccept;
//    private String btnreject;

    public FriendList(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
//        this.btnaccept=btnaccept;
//        this.btnreject=btnreject;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

//    public String getAcceptbtn() {
//        return btnaccept;
//    }
//
//    public String getRejectbtn() {
//        return btnreject;
//    }

}
