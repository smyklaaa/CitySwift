package com.example.cityswift.server.model;

import java.util.Date;

public class FriendshipModel {
    private Integer appUserId1;
    private Integer appUserId2;
    private Date friendshipDate;
    private boolean isAccepted;

    public FriendshipModel() {
    }

    public FriendshipModel(Integer appUserId1, Integer appUserId2, Date friendshipDate, boolean isAccepted) {
        this.appUserId1 = appUserId1;
        this.appUserId2 = appUserId2;
        this.friendshipDate = friendshipDate;
        this.isAccepted = isAccepted;
    }

    public Integer getAppUserId1() {
        return appUserId1;
    }

    public void setAppUserId1(Integer appUserId1) {
        this.appUserId1 = appUserId1;
    }

    public Integer getAppUserId2() {
        return appUserId2;
    }

    public void setAppUserId2(Integer appUserId2) {
        this.appUserId2 = appUserId2;
    }

    public Date getFriendshipDate() {
        return friendshipDate;
    }

    public void setFriendshipDate(Date friendshipDate) {
        this.friendshipDate = friendshipDate;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }
}
