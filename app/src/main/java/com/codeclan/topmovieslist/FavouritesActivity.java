package com.codeclan.topmovieslist;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);
        // - 1st step is in Movie class, these steps are to create a 2nd page loading with Favourite movies...
        // ...by clicking on a movie in the list view.
        // - 2nd - Got shared preference with a name FAVOURITES and made it private.
        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        // - 3rd - Got a string version of our favourite moie arraylist from shared preferences (Serialized)
        String favouriteMovies = sharedPref.getString("MyFavourites", new ArrayList<Movie>().toString());
        Log.d("FAVOURITES", favouriteMovies);
        // - 4th - Use GSON to deserialize the ArrayList (convert String format back to Arraylist)
        Gson gson = new Gson();
        // - 5th - TypeToken tells GSON what to convert JSON back to. in this case ArrayList.
        TypeToken<ArrayList<Movie>> movieArrayList = new TypeToken<ArrayList<Movie>>(){};
        // - 6th - gets JSON data and converts it to ArrayList<Movie>
        ArrayList<Movie> myFavourites = gson.fromJson(favouriteMovies, movieArrayList.getType());
        // - 7th - Create a new favourite movie object from the ListView item.
        Movie newFavouriteMovie = (Movie) getIntent().getSerializableExtra("movie");
        // - 8th - Add object to ArrayList.
        if(!myFavourites.contains(newFavouriteMovie)) {
            myFavourites.add(newFavouriteMovie);
        }
        // the above if statement will check if the movie is already in the favourite list....
        // .. if the if statement is not needed then the below commented line would only be needed.
        // myFavourites.add(newFavouriteMovie);
        Log.d("MY_FAVOURITES", myFavourites.toString());

        SharedPreferences.Editor editor = sharedPref.edit();

        // - 9th - convert ArrayList to JSON (String object) and save to shared preferences.
        editor.putString("MyFavourites", gson.toJson(myFavourites));
        editor.apply();
//      editor.clear(); - this is used to clear the arrayList, but placing it here will remove the
//                on each opening of the app, probably best to assign it to a button on the screen.

        Toast.makeText(this, "Movie Added", Toast.LENGTH_SHORT).show();
        // - 10th - loops through the ArrayList to populate the My Favourites page with the marked favourites.
        TextView list = (TextView)findViewById(R.id.favourites_list);
        String movieString = "";

        for (Movie movie : myFavourites){
            movieString += movie.getTitle() + " " + movie.getYear() + "\n";
        }

        list.setText(movieString);
    }
}
