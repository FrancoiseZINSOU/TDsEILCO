package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO
        Intent intent = new Intent(SearchActivity.this, MoviesActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search Movie");

        Resources resources = this.getResources();
        String lang = resources.getString(R.string.language);

        RecyclerView rvMovies = (RecyclerView)  findViewById(R.id.rvMoviesSearch);
        Button researchButton = findViewById(R.id.recherche);
        EditText et = findViewById(R.id.searchText);

        MovieService movieService=new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);


        researchButton.setOnClickListener(v -> {

            movieService.searchMovies(et.getText().toString(),lang).enqueue(new Callback<MoviesList>(){

                MoviesList popularMovies = new MoviesList();
                @Override
                public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                    if(response.body()!=null){
                        popularMovies=response.body();

                        MovieAdapter adapter = new MovieAdapter(popularMovies.getlMovies(), new MovieAdapter.ItemClickListener() {
                            @Override
                            public void onItemClick(Movie movie) {

                                Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
                                intent.putExtra("idMovie", movie.getId()); //envoyer id du film à la page details
                                intent.putExtra("titre", movie.getTitle()); //envoyer id du film à la page details
                                startActivity(intent);
                                finish();

                            }
                        });

                        rvMovies.setAdapter(adapter);
                        //rvMovies.setLayoutManager(new LinearLayoutManager(MoviesActivity.this));
                        rvMovies.setLayoutManager(new GridLayoutManager(SearchActivity.this,2));

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

        });

    }
}