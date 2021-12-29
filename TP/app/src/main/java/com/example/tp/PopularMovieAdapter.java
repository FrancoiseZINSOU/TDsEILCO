package com.example.tp;

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

public class PopularMovieAdapter extends RecyclerView.Adapter<PopularMovieAdapter.ViewHolder> {

    private final List<Movie> listMovies;

    public PopularMovieAdapter(List<Movie> listMovies){
        this.listMovies=listMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_movie,parent, false);

        return new ViewHolder(contactView);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Movie movie = listMovies.get(position);

        ImageView image = holder.urlImageView;

        Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w342"+movie.getPosterUrl()).into(image);
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView urlImageView;

        public ViewHolder(View itemView){
            super(itemView);

            urlImageView=(ImageView) itemView.findViewById(R.id.imageMovie);
        }
    }

}
