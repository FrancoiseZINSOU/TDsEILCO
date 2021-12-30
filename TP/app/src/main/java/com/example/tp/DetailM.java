package com.example.tp;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DetailM {

    @SerializedName("overview")
    private String resume ;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("poster_path")
    private String posterUrl;

    @SerializedName("genres")
    private List<Genre> genres ;

    public String getResume() {
        return resume;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public String getPosterUrl() {
        return posterUrl;
    }
}
