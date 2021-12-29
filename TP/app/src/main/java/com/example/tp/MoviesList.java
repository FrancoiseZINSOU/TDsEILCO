package com.example.tp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesList {
    @SerializedName("total_results")
    private int resultatsTotal;

    @SerializedName("results")
    private List<Movie> lMovies;

    public int getResultatsTotal() {
        return resultatsTotal;
    }

    public List<Movie> getlMovies() {
        return lMovies;
    }
}
