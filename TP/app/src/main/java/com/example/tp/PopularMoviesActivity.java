package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class PopularMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_movies);
    }
}