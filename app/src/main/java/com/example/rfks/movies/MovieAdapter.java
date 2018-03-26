package com.example.rfks.movies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by rfks on 25/02/2018.
 */

public class MovieAdapter extends ArrayAdapter<Movie>{

    public MovieAdapter(Activity context, List<Movie> movies){
        super(context,0,movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_list_item,parent,false);
        }
        ImageView posterView = (ImageView) convertView.findViewById(R.id.poster);
        Picasso.with(getContext()).load("http://image.tmdb.org/t/p/w185/"+movie.poster).into(posterView);
        //posterView.setImageResource(movie.poster);
        return convertView;
    }
}

