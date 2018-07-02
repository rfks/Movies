package com.example.rfks.movies;

import com.google.gson.annotations.SerializedName;

public class Trailer {

    String key;
    String name;

    public Trailer (String key,String name) {

        //@SerializedName("key")
        this.key=key;
        //@SerializedName("name")
        this.name=name;
    }

    public String getKey(){
        return key;
    }

    public String getName() {
        return name;
    }
}
