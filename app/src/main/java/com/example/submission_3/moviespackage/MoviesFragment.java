package com.example.submission_3.moviespackage;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.submission_3.adapter.MoviesAdapter;
import com.example.submission_3.R;
import com.example.submission_3.viewmodel.MyViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private MoviesAdapter adapter;
    private MyViewModel myViewModel;
    private ProgressBar progressBar;
    //To fetch data
    private Observer<List<Movie>> getMovies = new Observer<List<Movie>>() {
        @Override
        public void onChanged(@Nullable List<Movie> movies) {
            if (movies != null) {
                adapter = new MoviesAdapter(getContext(), movies);
                mRecyclerView.setAdapter(adapter);
                showLoading(false);
            }
        }
    };
    public MoviesFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_movies, container, false);
        mRecyclerView = v.findViewById(R.id.rv_movies);
        progressBar = v.findViewById(R.id.progressBar);
        showLoading(true);
        return v;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        myViewModel = ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        myViewModel.getMovies(getActivity()).observe(getActivity(), getMovies);
    }




    //To show progressbar or not
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}




