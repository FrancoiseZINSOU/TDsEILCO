package com.example.tp6bis;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Repos {

    @SerializedName("total_count")
    private int compte=0;

    @SerializedName("items") // retourne une liste de repo
    private List<Repo> lRepos;

    public int getCompte() {
        return compte;
    }

    public List<Repo> getlRepos() {
        return lRepos;
    }
}
