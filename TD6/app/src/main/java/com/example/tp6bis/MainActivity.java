package com.example.tp6bis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import  java.util.Collection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Permet de construire l'outil d'appel de l'API
        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);

        // Recuperer les layout en java
        TextView tv = (TextView) findViewById(R.id.textView);
        EditText et = (EditText) findViewById(R.id.userText);
        Button button = (Button) findViewById(R.id.chercher);

        // recherche la liste des repos d'un utilisateur
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                githubService.listRepos(et.getText().toString()).enqueue(new  Callback<List<Repo>>(){
                    @Override
                    public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {

                        //On récuper la liste des repos trouvé pour le user
                        if(response.body()!= null){
                            afficherRepos(response.body());
                            String fullName = response.body().get(0).getFullname(); //recup juste le nom du premier repos de l'utilisateur
                            tv.setText(fullName);
                        }
                        else{
                            Toast.makeText(getBaseContext(),"aucun repos trouvé ", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Repo>> call, Throwable t) {
                    }
                });

            }
        });

        //

        }

    public void afficherRepos( List<Repo> repos) {
        Toast.makeText(getBaseContext(),"nombre de dépots : "+repos.size(), Toast.LENGTH_LONG).show();
    }


}