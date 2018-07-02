package com.example.rfks.movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetData {

    @GET("/3/movie/{id}/videos")
    Call<TrailerResponse> getTrailers(@Path("id") Integer id,@Query("api_key") String api_key);

}
