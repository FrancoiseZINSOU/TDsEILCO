package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {

    ListView simpleList;
    ArrayList<String> genreList = new ArrayList<>() ;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //TODO
       Intent intent = new Intent(DetailsActivity.this, MoviesActivity.class);
       startActivity(intent);
       finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Permet de construire l'outil d'appel de l'API
        MovieService movieService = new Retrofit.Builder()
                .baseUrl(MovieService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);

        // Recuperer les layout en java
        TextView text = findViewById(R.id.textViewD1);
        TextView date = findViewById(R.id.textViewD3);
        ImageView urlImageView = findViewById(R.id.imageDetail);

        //Recuperation de l'id du film et du titre
        Intent intent = getIntent();
        String id = intent.getStringExtra("idMovie");
        String titre = intent.getStringExtra("titre");
        setTitle(titre);

        //Toast.makeText(this,id,Toast.LENGTH_LONG).show();

        //Appel Rx avec ID du film
        movieService.detailMovie(id).enqueue(new Callback<DetailM>() {
            @Override
            public void onResponse(Call<DetailM> call, Response<DetailM> response) {

                //On récuper les details du film
                if(response.body()!= null){

                    Glide.with(urlImageView).load("https://image.tmdb.org/t/p/w342"+response.body().getPosterUrl()).into(urlImageView);

                    String textResume = response.body().getResume(); //recup juste le resume
                    text.setText(textResume);

                    String textDate = response.body().getReleaseDate(); //recup juste la date
                    date.setText(textDate);

                    //récuper les noms des genres
                    for (Genre genre : response.body().getGenres()){
                        genreList.add(genre.getName());
                    }

                    //Remplacer une valeur par une autre dans une liste
                    for (String s : genreList) {
                        genreList.set(genreList.indexOf(s),"\u2013 " + s);
                    }

                    simpleList = findViewById(R.id.simpleListView);
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(DetailsActivity.this, R.layout.activity_listview, R.id.textView, genreList);
                    simpleList.setAdapter(arrayAdapter);

                }
                else{
                    Toast.makeText(getBaseContext(),"aucun film trouvé ", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<DetailM> call, Throwable t) {

            }
        });


    }

}