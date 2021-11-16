package com.vergiean.movieapps;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ImageView imgPosterDetail = findViewById(R.id.imgPosterDetail);
        TextView tvMovieTitle = findViewById(R.id.tvTitleDetail);
        TextView tvMovieDesc = findViewById(R.id.tvDescDetail);
        TextView tvMovieRelease = findViewById(R.id.tvReleaseDetail);
        ImageView imgBackDrop = findViewById(R.id.imgBackdrop);
        TextView tvMovieRating = findViewById(R.id.tvRatingDetail);
        TextView tvMovieViewer = findViewById(R.id.tvMovieViewer);
        TextView tvMovieLang = findViewById(R.id.tvMovieLang);

        Glide.with(DetailMovieActivity.this)
                .load("https://themoviedb.org/t/p/w500/" + movie.getImageURL())
                .into(imgPosterDetail);
        tvMovieTitle.setText(movie.getTitle());
        tvMovieRelease.setText(movie.getRelease());
        tvMovieDesc.setText(movie.getDescription());
        Glide.with(DetailMovieActivity.this)
                .load("https://themoviedb.org/t/p/w500/" + movie.getBackdropURL())
                .into(imgBackDrop);
        tvMovieRating.setText(movie.getRating());
        tvMovieViewer.setText(movie.getViewer());
        tvMovieLang.setText(movie.getLanguage());

        AppBarLayout appBar = findViewById(R.id.appBar);

        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        appBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if (verticalOffset < -500) {
                collapsingToolbarLayout.setTitle(movie.getTitle());
                collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
            } else {
                collapsingToolbarLayout.setTitle("");
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}