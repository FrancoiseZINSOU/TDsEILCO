package com.example.tp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final List<Movie> listMovies;
    private ItemClickListener mItemListener;

    public MovieAdapter(List<Movie> listMovies, ItemClickListener mItemListener){
        this.listMovies=listMovies;
        this.mItemListener = mItemListener;
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
        String path = "https://image.tmdb.org/t/p/w342"+movie.getPosterUrl();

        Glide.with(holder.itemView).load(path).into(image);

       // Picasso.with(holder.itemView).load(path).resize(250, 250).centerCrop().into(image);

        //en cliquant sur une image
        holder.itemView.setOnClickListener(view ->{
                mItemListener.onItemClick(listMovies.get(position)); // La position du film dans le rv
        });
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public interface ItemClickListener{
        void onItemClick(Movie movie);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView urlImageView;

        public ViewHolder(View itemView){
            super(itemView);

            urlImageView=(ImageView) itemView.findViewById(R.id.imageMovie);
        }
    }

}
