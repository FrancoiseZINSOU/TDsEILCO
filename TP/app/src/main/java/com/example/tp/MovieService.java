package com.example.tp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
    public static final String ENDPOINT="https://api.themoviedb.org/3/movie/";

    @GET("{type}?api_key=3b9efd6f530227ba69de00b76ba5a748")
    Call<MoviesList> listMovies(@Path("type") String type);
}
