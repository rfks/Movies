package com.example.rfks.movies;

public class Review {

    String author;
    String content;

    public Review(String author, String content) {

        //@SerializedName("key")
        this.author=author;
        //@SerializedName("name")
        this.content=content;
    }

    public String getAuthor(){
        return author;
    }

    public String getContent() {
        return content;
    }
}
