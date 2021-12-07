package com.example.newslist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newslist.databinding.ActivityLoginBinding;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        setTitle(getLocalClassName());


        Button detailButton = (Button) findViewById(R.id.details);
        detailButton.setOnClickListener(v -> {

            Intent intent = new Intent(NewsActivity.this, DetailsActivity.class);
            startActivity(intent);
            finish();
        });

        Button logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(NewsActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //Lancement d’une activité pour afficher une page Web

        Button libelleButton = (Button) findViewById(R.id.libelle);
        libelleButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String url = "https://news.google.com/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                finish();
            }
        });

    }
}
