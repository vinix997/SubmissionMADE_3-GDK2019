package com.example.submission_3.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.widget.Toast;

import com.example.submission_3.api.ApiMovie;
import com.example.submission_3.api.ApiTV;
import com.example.submission_3.moviespackage.Movie;
import com.example.submission_3.moviespackage.MovieResponse;
import com.example.submission_3.tvshowpackage.TVShow;
import com.example.submission_3.tvshowpackage.TVShowResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyViewModel extends ViewModel {
    private static final String API_KEY = "c184f7e4cec0ace78948ef31fbc18b8e";
    private static final String BASE_URL = "http://api.themoviedb.org/3/";
    private MutableLiveData<List<Movie>> listMovies = new MutableLiveData<>();
    private MutableLiveData<List<TVShow>> listTvs = new MutableLiveData<>();

    public LiveData<List<TVShow>> getTvs(Context context) {
        loadTvs(context);
        return listTvs;
    }

    public LiveData<List<Movie>> getMovies(Context context) {

        loadMovies(context);

        return listMovies;
    }

    private void loadMovies(final Context context  ) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiMovie apiInterface = retrofit.create(ApiMovie.class);
        Call<MovieResponse> call = apiInterface.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                listMovies.setValue(response.body().getResults());
                Toast.makeText(context,"Success Fetching Movie Data",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(context,"Error Fetching Movie Data",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadTvs(final Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiTV apiInterface = retrofit.create(ApiTV.class);
        Call<TVShowResponse> call = apiInterface.getTopRatedTvShow(API_KEY);
        call.enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                listTvs.setValue(response.body().getResults());
                Toast.makeText(context,"Success Fetching TV Data",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {
               Toast.makeText(context,"Error Fecthing TV Data",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
