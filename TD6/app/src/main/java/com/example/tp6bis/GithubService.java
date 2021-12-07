package com.example.tp6bis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {
    public static final String ENDPOINT = "https://api.github.com";

    @GET("/users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);// retourne une liste l'API commence par [

    @GET("/search/repositories")
    Call<Repos> searchRepos(@Query("q") String query); // retourne un objet Repos l'API commence par {

}