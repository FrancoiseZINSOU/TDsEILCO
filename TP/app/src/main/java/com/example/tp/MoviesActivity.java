package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_movies);
        setTitle("Popular Movies");

            appelRx("popular"); //récup les films popular au lancement

        Button popularButton = findViewById(R.id.popular);
        Button upComingButton = findViewById(R.id.upcoming);

        popularButton.setOnClickListener(v -> {
            setTitle("Popular Movies");
            appelRx("popular");
        });

        upComingButton.setOnClickListener(v -> {
            setTitle("Upcoming Movies");
            appelRx("upcoming"); //recup les films upcoming
        });

    }

    // Creation d'une fonction pour l'appel rx
    public void appelRx(String type){

        RecyclerView rvMovies = (RecyclerView)  findViewById(R.id.rvMovies);

        MovieService movieService=new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        movieService.listMovies(type).enqueue(new Callback<MoviesList>(){

            MoviesList popularMovies = new MoviesList();
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                if(response.body()!=null){
                    popularMovies=response.body();

                    MovieAdapter adapter = new MovieAdapter(popularMovies.getlMovies(), new MovieAdapter.ItemClickListener() {
                        @Override
                        public void onItemClick(Movie movie) {

                            Intent intent = new Intent(MoviesActivity.this, DetailsActivity.class);
                            intent.putExtra("idMovie", movie.getId()); //envoyer id du film à la page details
                            intent.putExtra("titre", movie.getTitle()); //envoyer id du film à la page details
                            startActivity(intent);
                            finish();

                        }
                    });

                    rvMovies.setAdapter(adapter);
                    //rvMovies.setLayoutManager(new LinearLayoutManager(MoviesActivity.this));
                    rvMovies.setLayoutManager(new GridLayoutManager(MoviesActivity.this,2));

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