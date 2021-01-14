package com.example.mytravelguide.Model;

import java.io.Serializable;

public class tourGuideModel implements Serializable {
    private static final long ID =1L;
    private String address;
    private String business_Name;
    private String isOpen_Now;
    private  String poster;
    private int ratings;

    public tourGuideModel(){
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusiness_Name() {
        return business_Name;
    }

    public void setBusiness_Name(String business_Name) {
        this.business_Name = business_Name;
    }

    public String isOpen_Now() {
        return isOpen_Now;
    }

    public void setOpen_Now(String open_Now) {
        this.isOpen_Now = open_Now;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
}
