package com.example.rfks.movies;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rfks.movies.data.MoviesContract;
import com.example.rfks.movies.data.MoviesProvider;
import com.squareup.picasso.Picasso;

import java.net.URI;


/**
 * Created by rfks on 26/03/2018.
 */

public class DetailsActivity extends AppCompatActivity {


    private Movie movieDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        if (savedInstanceState == null || !savedInstanceState.containsKey("movie")) {
            movieDetails = getIntent().getParcelableExtra("movie");
        } else {
            movieDetails = savedInstanceState.getParcelable("movie");
        }

        TextView titleText = (TextView) findViewById(R.id.title);
        titleText.setText(movieDetails.title);
        ImageView posterImage = (ImageView) findViewById(R.id.poster);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185/" + movieDetails.poster).into(posterImage);
        TextView releasedText = (TextView) findViewById(R.id.released);
        releasedText.setText(movieDetails.released);
        TextView avg_voteText = (TextView) findViewById(R.id.avg_rating);
        avg_voteText.setText(movieDetails.avg_vote);
        TextView synopsisText = (TextView) findViewById(R.id.synopsis);
        synopsisText.setText(movieDetails.synopsis);

        final CheckBox favBox = (CheckBox) findViewById(R.id.checkBox);
        if (favourite(movieDetails.id)){
            favBox.setChecked(true);
        } else favBox.setChecked(false);

        favBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (favBox.isChecked()){
                    ContentValues cv = new ContentValues();
                    cv.put(MoviesContract.MovieEntry._ID,movieDetails.id);
                    cv.put(MoviesContract.MovieEntry.COLUMN_TITLE,movieDetails.title);
                    getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI,cv);
                } else {
                    String where = MoviesContract.MovieEntry._ID + "=?";
                    String[] selectionArgs = {movieDetails.id.toString()};
                    getContentResolver().delete(MoviesContract.MovieEntry.CONTENT_URI,where,selectionArgs);
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movie", movieDetails);
        super.onSaveInstanceState(outState);
    }

    public boolean favourite(Integer id){
        /*Cursor c1 =getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,null,null,null,null);
        Log.v("**********************",c1.getString(c1.getColumnIndex("_id")));*/
        String[] projection = {MoviesContract.MovieEntry._ID};
        String selection = MoviesContract.MovieEntry._ID + "=?";
        String[] selectionArgs = {id.toString()};
        Cursor cursor = getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,projection,selection,selectionArgs,null);
        if (cursor == null || cursor.getCount() < 1) {
            //Log.v("++++++++++++++++++++","nem jo");
            return false;
        } else {
            //Log.v("--------------------",cursor.getString(cursor.getColumnIndex("_id")));
            return true;
        }
    }
}