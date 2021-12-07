package com.example.tp5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Lookup the recyclerview in activity layout
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        //Initialisation des contacts
        contacts.add(new Contact("Jean","Pierre","https://fr.wikipedia.org/wiki/Jean-Pierre_Pernaut#/media/Fichier:Renaud_Beretti_et_Jean-Pierre_Pernaut_(cropped).jpg"));
        contacts.add(new Contact("Jeanne","D'Arc","https://histoire-image.org/sites/default/jeanne-arc-sacre-charlesvii.jpg"));
        contacts.add(new Contact("Pierre","Menez","https://www.francetvinfo.fr/pictures/oo8sfFWvOXysfJaAfKC-_EbruFE/0x166:5000x2979/944x531/filters:format(webp)/2018/03/12/phpxAauiA_1.jpg"));
        contacts.add(new Contact("Arthur","Rimbaut","https://www.causeur.fr/wp-content/uploads/2021/04/Alain-Vircondelet-Arthur-Rimbaud-dernier-voyage-768x466.jpg"));
        contacts.add(new Contact("Richard","Coeur de Lion","http://www.cosmovisions.com/images/Richard-Coeur-Lion.jpg"));
        contacts.add(new Contact("Zinedine","Zidane","https://upload.wikimedia.org/wikipedia/commons/7/7c/Zidane_Zizu.jpg"));
        contacts.add(new Contact("Yannick","Noah","https://www.premium-communication.fr/cache/b/8/b/0/d/b8b0dbc2e29d285808fd096a30de721fc45fe9b1.jpeg"));

        // Création d'un adapter avec initialisation du contructeur avec notre liste de contacts
        ContactsAdapter adapter = new ContactsAdapter(contacts);

        //On notifie au recycleurview notre adapter
        rvContacts.setAdapter(adapter);

        //On déclare quelle type de LayoutManager on désire
        rvContacts.setLayoutManager(new LinearLayoutManager(this));


    }
}