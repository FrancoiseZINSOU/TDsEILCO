package com.example.tp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    String ENDPOINT="https://api.themoviedb.org/3/";

    @GET("movie/{type}?api_key=3b9efd6f530227ba69de00b76ba5a748")
    Call<MoviesList> listMovies(@Path("type") String type, @Query("language") String language);

    @GET("search/movie?api_key=3b9efd6f530227ba69de00b76ba5a748&language=en-US&page=1&include_adult=false")
    Call<MoviesList> searchMovies(@Query("query") String query, @Query("language") String language);

   // @GET("https://api.themoviedb.org/3/search/movie?api_key=<<api_key>>&language=en-US&page=1&include_adult=false")

    @GET("movie/{movie_id}?api_key=5338a30948af1ec2f1a0c232b10142e7&language=en-US")
    Call<DetailM> detailMovie(@Path("movie_id") String movieId, @Query("language") String language);

}