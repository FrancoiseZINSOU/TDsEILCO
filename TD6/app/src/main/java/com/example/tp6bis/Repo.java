package com.example.tp6bis;

import com.google.gson.annotations.SerializedName;

public class Repo {
    private int id;
    private String name;
    @SerializedName("full_name")
    private String fullname;
    private String html_url;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullname() {
        return fullname;
    }

    public String getHtml_url() {
        return html_url;
    }

}
