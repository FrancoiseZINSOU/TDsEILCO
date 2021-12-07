package com.example.newslist;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle(getLocalClassName());


        Button rollButton = findViewById(R.id.login);

        rollButton.setOnClickListener(v -> {

            // récupérer le nombre saisie
            EditText et = (EditText) findViewById(R.id.username);
            String nombreLettre = et.getText().toString();

            Intent intent = new Intent(LoginActivity.this, NewsActivity.class);
            intent.putExtra("login", nombreLettre);
            startActivity(intent);
            finish(); //cela va me permettre de détruire l'activité pour éviter un retour à la ligne
        });






    }

}