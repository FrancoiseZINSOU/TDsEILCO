package com.example.tp5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

    private  final List<Contact> nContacts;

    public ContactsAdapter(List<Contact> contacts) {
        this.nContacts = contacts;
    }


    @NonNull
    @Override
    //demande l'element qu il va recopier (dupliquer)
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_contact,parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ViewHolder holder, int position) {

        Contact contact = nContacts.get(position);

        TextView firstNameTextView  = holder.firstNameTextView;
        firstNameTextView.setText(contact.getmFirstName());

        TextView lastNameTextView  = holder.lastNameTextView;
        lastNameTextView.setText(contact.getmLastName());

        ImageView image = holder.imageView;
        Glide.with(holder.itemView).load(contact.getImageurl()).into(image);

    }

    @Override
    public int getItemCount() {
        return nContacts.size();
    }


    //
    public static  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView firstNameTextView;
        public TextView lastNameTextView;
        public ImageView imageView;

        public ViewHolder(View itemView){
            super(itemView);

            firstNameTextView = (TextView) itemView.findViewById(R.id.contact_firstname);
            lastNameTextView = (TextView) itemView.findViewById(R.id.contact_lastname);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }



    }
}
