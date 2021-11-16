package com.vergiean.movieapps;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    List<Movie> movies;

    public MovieAdapter( List<Movie> movies){
        this.movies = movies;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_list_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
     TextView movieTitle, movieRelease, movieRating;
        ImageView moviePosterImage;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            movieTitle = itemView.findViewById(R.id.tvMovieTitle);
            movieRelease = itemView.findViewById(R.id.tvMovieRelease);
            moviePosterImage = itemView.findViewById(R.id.imgMoviePoster);
            movieRating = itemView.findViewById(R.id.tvMovieRating);
        }

        public void bind(Movie movie){
            movieTitle.setText(movie.getTitle());
            movieRelease.setText( movie.getRelease());
            movieRating.setText(movie.getRating());
            Glide.with(moviePosterImage.getContext())
                    .load("https://themoviedb.org/t/p/w500/" + movie.getImageURL())
                    .into(moviePosterImage);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(),DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.EXTRA_MOVIE,movie);
                itemView.getContext().startActivity(intent);
            });
        }
    }

}
