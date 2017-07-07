package com.codeclan.topmovieslist;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.undo;

public class TopMoviesActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list);

        TopMovies topMovies = new TopMovies();
        ArrayList<Movie> list = topMovies.getList();

        TopMoviesAdapter movieAdapter = new TopMoviesAdapter(this, list);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(movieAdapter);
    }

    public void getMovie(View listItem){
        Movie movie = (Movie) listItem.getTag();
        StringBuilder sb = new StringBuilder();
        sb.append("You clicked: ");
        sb.append(movie.getTitle());
        Intent intent = new Intent(this, FavouritesActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);

//        Snackbar snackbar = Snackbar.make(listItem, sb.toString(), Snackbar.LENGTH_LONG);
//        snackbar.show();
//
//        snackbar.setAction("Say Hello", this);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Hello from Snackbar!", Toast.LENGTH_SHORT).show();
    }
}
