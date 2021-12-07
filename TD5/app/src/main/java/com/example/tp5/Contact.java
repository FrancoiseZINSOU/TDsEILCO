package com.example.tp5;

public class Contact {

    private String mFirstName;
    private String mLastName;
    private String imageurl;

    public Contact(String mFirstName, String mLastName, String imageurl) {
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.imageurl = imageurl;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public String getImageurl() {
        return imageurl;
    }
}
