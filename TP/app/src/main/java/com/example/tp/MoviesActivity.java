package com.example.tp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;


import android.content.res.Resources;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MoviesActivity extends AppCompatActivity {

    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_movies);
        setTitle("Popular Movies");

            appelRx("popular"); //récup les films popular au lancement

        Button popularButton = findViewById(R.id.popular);
        Button upComingButton = findViewById(R.id.upcoming);
        Button searchButton = findViewById(R.id.search);

        popularButton.setBackgroundColor(Color.BLACK);
        upComingButton.setBackgroundColor(Color.BLACK);
        searchButton.setBackgroundColor(Color.BLACK);

        popularButton.setOnClickListener(v -> {
            setTitle("Popular Movies");
            appelRx("popular");
        });

        upComingButton.setOnClickListener(v -> {
            setTitle("Upcoming Movies");
            appelRx("upcoming"); //recup les films upcoming
        });

        searchButton.setOnClickListener(v -> {

            Intent intent = new Intent(MoviesActivity.this, SearchActivity.class);
            startActivity(intent);
            finish();
            //appelRx("upcoming"); //recup les films upcoming
        });

    }

    // Creation d'une fonction pour l'appel rx
    public void appelRx(String type){

        resources = this.getResources();
        String lang = resources.getString(R.string.language);

        RecyclerView rvMovies = (RecyclerView)  findViewById(R.id.rvMovies);

        MovieService movieService=new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        movieService.listMovies(type,lang).enqueue(new Callback<MoviesList>(){

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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.langFR:
                setLocalLang("fr");
                appelRx("popular");
                resources = getBaseContext().getResources();
                Toast.makeText(getApplicationContext(), ""+resources.getString(R.string.language), Toast.LENGTH_SHORT).show();
                return true;

            case R.id.langEN:
                setLocalLang("en");
                appelRx("popular");
                resources = getBaseContext().getResources();
                Toast.makeText(getApplicationContext(), ""+resources.getString(R.string.language), Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.lang_title_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Permet de changer la langue locale
    public void setLocalLang(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        
    }
}