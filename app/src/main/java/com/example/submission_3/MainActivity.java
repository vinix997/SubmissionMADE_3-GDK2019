package com.example.submission_3;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.submission_3.adapter.ViewAdapter;
import com.example.submission_3.moviespackage.MoviesFragment;
import com.example.submission_3.tvshowpackage.TVFragment;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        String type = String.format(getResources().getString(R.string.movies));
        String type1 = String.format(getResources().getString(R.string.tv_show));
        adapter = new ViewAdapter(getSupportFragmentManager());
        adapter.AddFragment(new MoviesFragment(), "Movies");
        adapter.AddFragment(new TVFragment(), "TV Shows");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_local_movies_black_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_tv_black_24dp);
        tabLayout.getTabAt(0).setText(type);
        tabLayout.getTabAt(1).setText(type1);
        setTitle("Catalogue Movie");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_setting) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
