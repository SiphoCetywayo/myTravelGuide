package com.example.mytravelguide.Model;



public class tourGuideData  {
    private String address;
    private String business_Name;
    private String isOpen_Now;
    private  String poster;
    private String ratings;

    public tourGuideData(){
    }

    public tourGuideData(String address, String business_Name, String isOpen_Now, String poster, String ratings) {
        this.address = address;
        this.business_Name = business_Name;
        this.isOpen_Now = isOpen_Now;
        this.poster = poster;
        this.ratings = ratings;
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

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    @Override
    public String toString(){
        return  "Name: " +business_Name+
                "Address: "+address+
                "Hours: "+isOpen_Now+
                "Poster: "+poster+
                "Ratings: "+ratings;

    }
}
