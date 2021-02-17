package com.limecoders.informativeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.limecoders.informativeapp.databinding.ActivityMainBinding;
import com.limecoders.informativeapp.fragments.aboutfragment.AboutFragment;
import com.limecoders.informativeapp.fragments.listfragment.ListFragment;
import com.limecoders.informativeapp.fragments.mainfragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private ActionBar toolbar;
    private InterstitialAd mInterstitialAd;

    private boolean isAbout = false;
    private SharedPreferenceManager preferenceManager;
    private int previousClicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        toolbar = getSupportActionBar();

        toolbar.setTitle("");

        preferenceManager = new SharedPreferenceManager(this);

        if(getIntent().hasExtra("isAbout")){
            isAbout = getIntent().getBooleanExtra("isAbout",false);
        }else{
            isAbout = false;
        }

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createPersonalizedAd();
            }
        });

        binding.navigationView.setItemIconTintList(null);
        binding.navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if(!isAbout) {
            loadFragment(new MainFragment());
        }else{
            loadFragment(new ListFragment());
        }

    }

    private void createPersonalizedAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        loadBannerAd(adRequest);
        loadInterstitialAd(adRequest);
    }

    private void loadBannerAd(AdRequest adRequest){
        binding.adView.loadAd(adRequest);
    }

    private void loadInterstitialAd(AdRequest adRequest){
        InterstitialAd.load(this,getString(R.string.INTERSTITIAL_ID), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                Log.i("Interstitial", "onAdLoaded");

                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        Log.i("Interstitial", "The ad was dismissed.");
                        createPersonalizedAd();
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.i("Interstitial", "The ad failed to show.");
                        createPersonalizedAd();
                    }

                    @Override
                    public void onAdShowedFullScreenContent() {
                        // Called when fullscreen content is shown.
                        // Make sure to set your reference to null so you don't
                        // show it a second time.
                        mInterstitialAd = null;
                        Log.i("Interstitial", "The ad was shown.");
                    }
                });
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                Log.i("Interstitial", loadAdError.getMessage());
                mInterstitialAd = null;
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    showAd();

                    toolbar.setTitle("");
                    fragment = new MainFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_albums:

                    showAd();

                    toolbar.setTitle("");
                    fragment = new ListFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_artists:

                    showAd();

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

    public void showAd(){
        previousClicked = preferenceManager.getButtonClicked();
        if (mInterstitialAd!=null && previousClicked>3) {
            preferenceManager.setButtonClicked(0);
            mInterstitialAd.show(MainActivity.this);
        } else {
            previousClicked = preferenceManager.getButtonClicked();
            preferenceManager.setButtonClicked(previousClicked+1);
            Log.d("Interstitial", "The interstitial ad wasn't ready yet.");
        }
        Log.i("previousClicked",String.valueOf(preferenceManager.getButtonClicked()));
    }

}