package com.example.mytravelguide.Model;



public class tourGuideData  {
    private String address;
    private String business_Name;
    private String isOpen_Now;
    private  String poster;
    private String ratings;

    /*Integers assigned to each layout
     * these are declared static so they
     * can be accessed from class name itself
     * and final so that they are not modified
     * later*/
    public static final int LayoutOne = 0;
    public static final int LayoutTwo = 1;

    /*This variable viewItem specifies
     * which out of the two layouts is
     * expected in the given time*/
    private int viewType;

    /*String variables to hold TextViews
     * of the attractions fragments items*/
    String text_one, text_two, text_three;


    public tourGuideData(){
    }

    public tourGuideData(String text_one, String text_two, String text_three) {
        this.text_one = text_one;
        this.text_two = text_two;
        this.text_three = text_three;
    }

    public tourGuideData(String address, String business_Name, String isOpen_Now, String poster, String ratings) {
        this.address = address;
        this.business_Name = business_Name;
        this.isOpen_Now = isOpen_Now;
        this.poster = poster;
        this.ratings = ratings;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }

    public String getText_two() {
        return text_two;
    }

    public void setText_two(String text_two) {
        this.text_two = text_two;
    }

    public String getText_three() {
        return text_three;
    }

    public void setText_three(String text_three) {
        this.text_three = text_three;
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
