package com.example.tp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailsActivity extends AppCompatActivity {

    ListView simpleList;
    ArrayList<String> countryList = new ArrayList(Arrays.asList("India", "China", "australia", "Portugle", "America", "NewZealand")) ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Remplacer une valeur par une autre dans une liste
        for (String s : countryList) {
            countryList.set(countryList.indexOf(s),"\u2013 " + s);
        }

        simpleList = (ListView) findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }
}