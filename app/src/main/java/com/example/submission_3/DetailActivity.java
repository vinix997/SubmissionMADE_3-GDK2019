package com.example.submission_3;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.submission_3.moviespackage.Movie;
import com.example.submission_3.tvshowpackage.TVShow;

//Activity to show detail of the movie
public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "extra_data";
    ImageView detailPhoto;
    TextView detailTitle;
    TextView detailDate;
    TextView detailDesc;
    TextView ratingDesc;
    TextView detailStartDate;
    TextView detailRating;
    TextView detailPopularity;
    TextView detailLanguage;

    private boolean check;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        check = getIntent().getBooleanExtra("isMovie",true);
        if(check) {
            Movie movie = getIntent().getParcelableExtra(EXTRA_DATA);
            setContentView(R.layout.detail_movies);
            String poster = "https://image.tmdb.org/t/p/w500" + movie.getPosterPath();
            detailPhoto = findViewById(R.id.img_id);
            Glide.with(this)
                    .load(poster)
                    .apply(new RequestOptions().override(300, 300))
                    .into(detailPhoto);
            detailTitle = findViewById(R.id.title_id);
            detailTitle.setText(movie.getTitle());
            detailDate = findViewById(R.id.date_id);
            String date = String.format(getResources().getString(R.string.date));
            System.out.println(date);
            detailDate.setText(date + " " + movie.getReleaseDate());
            detailDesc = findViewById(R.id.desc_id);
            String overview = String.format(getResources().getString(R.string.overview));
            detailDesc.setText(overview + "\n" + movie.getOverview());
            ratingDesc = findViewById(R.id.rating_id);
            String rating = String.format(getResources().getString(R.string.rating));
            ratingDesc.setText(rating + " " + movie.getVoteAverage().toString());
            getSupportActionBar().setTitle(movie.getTitle());
        }
        else
        {
            setContentView(R.layout.detail_tvshows);
            TVShow tvShow = getIntent().getParcelableExtra(EXTRA_DATA);
            detailPhoto = findViewById(R.id.img_id);
            String poster = "https://image.tmdb.org/t/p/w500" + tvShow.getPosterPath();
            Glide.with(this)
                    .load(poster)
                    .apply(new RequestOptions().override(300, 300))
                    .into(detailPhoto);

            detailTitle = findViewById(R.id.title_id);
            detailTitle.setText(tvShow.getName());
            detailPopularity = findViewById(R.id.pop_id);
            String popularity = String.format(getResources().getString(R.string.popularity));
            detailPopularity.setText(popularity+" " + tvShow.getPopularity().toString());
            detailRating = findViewById(R.id.rating_id);
            String rating = String.format(getResources().getString(R.string.rating));
            detailRating.setText(rating+" " + tvShow.getVoteAverage().toString());
            detailDesc = findViewById(R.id.desc_id);
            String overview = String.format(getResources().getString(R.string.overview));
            detailDesc.setText(overview+"\n" + tvShow.getOverview());
            detailStartDate = findViewById(R.id.startDate_id);
            String date = String.format(getResources().getString(R.string.fAirDate));
            detailStartDate.setText(date+" " + tvShow.getFirstAirDate());
            detailLanguage = findViewById(R.id.language_id);
            String lang = String.format(getResources().getString(R.string.oriLang));
            detailLanguage.setText(lang+" " + tvShow.getOriginalLanguage().toUpperCase());
            getSupportActionBar().setTitle(tvShow.getName());
        }
    }
}
