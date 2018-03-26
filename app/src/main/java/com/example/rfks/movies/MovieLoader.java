package com.example.rfks.movies;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;

/**
 * Created by rfks on 25/03/2018.
 */

/**
 * Loads a list of articles by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class MovieLoader extends AsyncTaskLoader<List<Movie>> {

    /** Tag for log messages */
    private static final String LOG_TAG = MovieLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link MovieLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public MovieLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Movie> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of movies.
        List<Movie> movies = Utils.fetchMovieData(mUrl);
        return movies;
    }
}

