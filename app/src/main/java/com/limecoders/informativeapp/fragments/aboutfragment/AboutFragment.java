package com.limecoders.informativeapp.fragments.aboutfragment;

import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.limecoders.informativeapp.MainActivity;
import com.limecoders.informativeapp.R;
import com.limecoders.informativeapp.SharedPreferenceManager;
import com.limecoders.informativeapp.SubRecipeActivity;


public class AboutFragment extends Fragment {

    private AboutViewModel mViewModel;
    private InterstitialAd mInterstitialAd;

    private TextView tab1, listFragment;
    private boolean isList;

    public static AboutFragment newInstance() {
        return new AboutFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_fragment, container, false);

        preferenceManager = new SharedPreferenceManager(getActivity());

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createPersonalizedAd();
            }
        });

        tab1 = view.findViewById(R.id.tab1);
        listFragment = view.findViewById(R.id.listFragment);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AboutViewModel.class);
        // TODO: Use the ViewModel

        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isList = false;
                showAd();
            }
        });

        listFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isList = true;
                showAd();

            }
        });

    }
    private void createPersonalizedAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        loadInterstitialAd(adRequest);
    }

    private void loadInterstitialAd(AdRequest adRequest){
        InterstitialAd.load(getActivity(),getString(R.string.INTERSTITIAL_ID), adRequest, new InterstitialAdLoadCallback() {
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
                        if(isList) {
                            startActivity(new Intent(getActivity(), MainActivity.class)
                                    .putExtra("isAbout", true));
                            try {
                                getActivity().finish();
                                getActivity().finishAffinity();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            createPersonalizedAd();
                        }else {
                            startActivity(new Intent(getActivity(), SubRecipeActivity.class)
                                    .putExtra("isAbout",true).putExtra("subCategoryName","目录"));
                        }
                    }

                    @Override
                    public void onAdFailedToShowFullScreenContent(AdError adError) {
                        // Called when fullscreen content failed to show.
                        Log.i("Interstitial", "The ad failed to show.");

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

    private SharedPreferenceManager preferenceManager;
    private int previousClicked;

    public void showAd(){
        previousClicked = preferenceManager.getButtonClicked();
        if (mInterstitialAd!=null && previousClicked>3) {
            preferenceManager.setButtonClicked(0);
            mInterstitialAd.show(getActivity());
        } else {
            previousClicked = preferenceManager.getButtonClicked();
            preferenceManager.setButtonClicked(previousClicked + 1);
            Log.d("Interstitial", "The interstitial ad wasn't ready yet.");
            if (isList) {
                startActivity(new Intent(getActivity(), MainActivity.class)
                        .putExtra("isAbout", true));
                try {
                    getActivity().finish();
                    getActivity().finishAffinity();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else {
                startActivity(new Intent(getActivity(), SubRecipeActivity.class)
                        .putExtra("isAbout",true).putExtra("subCategoryName","目录"));
            }
        }
        Log.i("previousClicked",String.valueOf(preferenceManager.getButtonClicked()));
    }
}