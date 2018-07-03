package com.example.rfks.movies;

import java.util.List;

public class ReviewResponse {

    private int id;
    private List<Review> results;

    public List<Review> getResults() {
        return results;
    }
}
