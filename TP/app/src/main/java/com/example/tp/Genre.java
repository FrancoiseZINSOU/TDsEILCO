package com.example.tp;

import com.google.gson.annotations.SerializedName;

public class Genre {

    private String id;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
