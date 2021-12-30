package com.example.tp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieService {

    String ENDPOINT="https://api.themoviedb.org/3/movie/";

    @GET("{type}?api_key=3b9efd6f530227ba69de00b76ba5a748")
    Call<MoviesList> listMovies(@Path("type") String type);

    @GET("{movie_id}?api_key=5338a30948af1ec2f1a0c232b10142e7&language=en-US")
    Call<DetailM> detailMovie(@Path("movie_id") String movieId);
}