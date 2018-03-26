package com.example.rfks.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;



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

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movie", movieDetails);
        super.onSaveInstanceState(outState);
    }

}