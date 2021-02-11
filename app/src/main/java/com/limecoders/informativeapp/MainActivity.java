package com.limecoders.informativeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.limecoders.informativeapp.databinding.ActivityMainBinding;
import com.limecoders.informativeapp.fragments.aboutfragment.AboutFragment;
import com.limecoders.informativeapp.fragments.listfragment.ListFragment;
import com.limecoders.informativeapp.fragments.mainfragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        toolbar = getSupportActionBar();

        toolbar.setTitle("");

        binding.navigationView.setItemIconTintList(null);
        binding.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(new MainFragment());

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle("");
                    fragment = new MainFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_albums:
                    toolbar.setTitle("");
                    fragment = new ListFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_artists:
                    toolbar.setTitle("");
                    fragment = new AboutFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

}