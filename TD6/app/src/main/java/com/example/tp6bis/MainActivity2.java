package com.example.tp6bis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity2 extends AppCompatActivity {

    Repos repos = new Repos();
    // Recuperer les layout en java
    EditText et ;
    Button button ;
    RecyclerView rv ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_repos);

         et = (EditText) findViewById(R.id.searchText);
         button = (Button) findViewById(R.id.recherche);
         rv = (RecyclerView) findViewById(R.id.allRepos);

        // Permet de construire l'outil d'appel de l'API
        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                githubService.searchRepos(et.getText().toString()).enqueue(new  Callback<Repos>(){

                    @Override
                    public void onResponse(Call<Repos> call, Response<Repos> response) {

                        //On récuper la liste des repos trouvé pour le user
                        if(response.body()!= null){
                            repos = response.body();

                            // Création d'un adapter avec initialisation du contructeur avec notre liste de contacts
                            ReposAdapter adapter = new ReposAdapter(repos.getlRepos());

                            //On notifie au recycleurview notre adapter
                            rv.setAdapter(adapter);

                            //On déclare quelle type de LayoutManager on désire
                            rv.setLayoutManager(new LinearLayoutManager(MainActivity2.this));
                        }
                        else{
                            Toast.makeText(getBaseContext(),"aucun repos trouvé ", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Repos> call, Throwable t) {
                    }
                });

            }
        });







    }
}
