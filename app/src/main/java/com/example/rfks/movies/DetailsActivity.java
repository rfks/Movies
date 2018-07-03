package com.example.rfks.movies;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rfks.movies.data.MoviesContract;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


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
                    cv.put(MoviesContract.MovieEntry.COLUMN_RELEASED,movieDetails.released);
                    cv.put(MoviesContract.MovieEntry.COLUMN_POSTER,movieDetails.poster);
                    cv.put(MoviesContract.MovieEntry.COLUMN_AVG_VOTE,movieDetails.avg_vote);
                    cv.put(MoviesContract.MovieEntry.COLUMN_SYNOPSIS,movieDetails.synopsis);
                    getContentResolver().insert(MoviesContract.MovieEntry.CONTENT_URI,cv);
                } else {
                    String where = MoviesContract.MovieEntry._ID + "=?";
                    String[] selectionArgs = {movieDetails.id.toString()};
                    Uri currentItemUri = ContentUris.withAppendedId(MoviesContract.MovieEntry.CONTENT_URI,movieDetails.id);
                    getContentResolver().delete(currentItemUri,where,selectionArgs);
                }
            }
        });

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.trailers_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final RecyclerView recyclerView2 = (RecyclerView) findViewById(R.id.reviews_recycler_view);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));


        /*Create handle for the RetrofitInstance interface*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GetData service = retrofit.create(GetData.class);
        Call<TrailerResponse> call = service.getTrailers(movieDetails.id,BuildConfig.MOVIE_DB_API_KEY);
        call.enqueue(new Callback<TrailerResponse>() {
            @Override
            public void onResponse(Call<TrailerResponse> call, Response<TrailerResponse> response) {
                int statusCode = response.code();
                List<Trailer> trailers = response.body().getResults();
                recyclerView.setAdapter(new TrailerAdapter(trailers, R.layout.trailer_list_item, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TrailerResponse> call, Throwable t) {
                // Log error here since request failed
                //Log.e(TAG, t.toString());
            }
        });

        Call<ReviewResponse> call2 = service.getReviews(movieDetails.id,BuildConfig.MOVIE_DB_API_KEY);
        call2.enqueue(new Callback<ReviewResponse>() {
            @Override
            public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
                int statusCode = response.code();
                List<Review> reviews = response.body().getResults();
                recyclerView2.setAdapter(new ReviewAdapter(reviews, R.layout.review_list_item, getApplicationContext()));
            }

            @Override
            public void onFailure(Call<ReviewResponse> call, Throwable t) {
                // Log error here since request failed
                //Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable("movie", movieDetails);
        super.onSaveInstanceState(outState);
    }

    public boolean favourite(Integer id){
        String[] projection = {MoviesContract.MovieEntry._ID};
        String selection = MoviesContract.MovieEntry._ID + "=?";
        String[] selectionArgs = {id.toString()};
        Cursor cursor = getContentResolver().query(MoviesContract.MovieEntry.CONTENT_URI,projection,selection,selectionArgs,null);
        if (cursor == null || cursor.getCount() < 1) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }

}