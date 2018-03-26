package com.example.rfks.movies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rfks on 25/02/2018.
 */
//Movie details layout contains title, release date, movie poster, vote average, and plot synopsis.
public class Movie implements Parcelable{
    String title;
    String released;
    String poster;
    String avg_vote;
    String synopsis;

    public Movie (String title,String released,String poster,String avg_vote,String synopsis){
        this.title=title;
        this.released=released;
        this.poster=poster;
        this.avg_vote=avg_vote;
        this.synopsis=synopsis;
    }

    private Movie(Parcel in){
        title = in.readString();
        released = in.readString();
        poster = in.readString();
        avg_vote = in.readString();
        synopsis = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String toString() { return title + "--" + released + "--" + poster + "--" + avg_vote + "--" + synopsis; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(released);
        parcel.writeString(poster);
        parcel.writeString(avg_vote);
        parcel.writeString(synopsis);
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }

    };
}

