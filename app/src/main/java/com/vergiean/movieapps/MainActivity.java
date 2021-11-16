package com.vergiean.movieapps;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Movie> movies;
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rvListMovie);
        movies = new ArrayList<>();
        getApi();

    }

    private void getApi() {
        String url = "https://r3i.my.id/wew.json";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                runOnUiThread(() -> Toast.makeText(MainActivity.this, getResources().getString(R.string.failureConnect), Toast.LENGTH_LONG).show());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseData = Objects.requireNonNull(response.body()).string();
                try {
                    JSONObject objData = new JSONObject(responseData);
                    final JSONArray results = objData.getJSONArray("results");
                    for (int i = 0; i < results.length(); i++) {
                        Movie movie = new Movie(
                                new JSONObject(results.get(i).toString()).getString("title"),
                                new JSONObject(results.get(i).toString()).getString("release_date"),
                                new JSONObject(results.get(i).toString()).getString("overview"),
                                new JSONObject(results.get(i).toString()).getString("poster_path"),
                                new JSONObject(results.get(i).toString()).getString("backdrop_path"),
                                new JSONObject(results.get(i).toString()).getString("vote_average"),
                                new JSONObject(results.get(i).toString()).getString("popularity"),
                                new JSONObject(results.get(i).toString()).getString("original_language")


                        );
                        movies.add(movie);
                    }


                    runOnUiThread(() -> {
                        adapter = new MovieAdapter(movies);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2, RecyclerView.VERTICAL, false));
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAbout:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.menuSetting:
                Intent intentLang = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intentLang);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

