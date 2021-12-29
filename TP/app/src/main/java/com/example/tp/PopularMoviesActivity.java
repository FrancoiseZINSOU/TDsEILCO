package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PopularMoviesActivity extends AppCompatActivity {

    MoviesList popularMovies = new MoviesList();

    RecyclerView rvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_movies);

        rvMovies = (RecyclerView)  findViewById(R.id.rvMovies);

        MovieService movieService=new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        movieService.listMovies("popular").enqueue(new Callback<MoviesList>(){


            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                    if(response.body()!=null){
                        popularMovies=response.body();

                        PopularMovieAdapter adapter = new PopularMovieAdapter(popularMovies.getlMovies());

                        rvMovies.setAdapter(adapter);

                        //rvMovies.setLayoutManager(new LinearLayoutManager(PopularMoviesActivity.this));

                        rvMovies.setLayoutManager(new GridLayoutManager(PopularMoviesActivity.this,2));
                    }
                    else{
                        System.out.println("CC");
                    }
            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                //TODO
            }
        });

    }
}