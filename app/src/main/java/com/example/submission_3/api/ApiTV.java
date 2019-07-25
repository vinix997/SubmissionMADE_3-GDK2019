package com.example.submission_3.api;

import com.example.submission_3.tvshowpackage.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiTV {
    @GET("tv/top_rated")
    Call<TVShowResponse> getTopRatedTvShow(@Query("api_key") String apiKey);
}
