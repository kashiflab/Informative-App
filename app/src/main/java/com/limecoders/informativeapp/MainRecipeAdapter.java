package com.limecoders.informativeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.limecoders.informativeapp.pojo.MainRecipeModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MainRecipeAdapter extends RecyclerView.Adapter<MainRecipeAdapter.ViewHolder> {
    private Context context;
    private boolean isMain;
    private InterstitialAd mInterstitialAd;
    private List<MainRecipeModel> modelList = new ArrayList<>();

    private MainRecipeModel model2;

    private int previousClicked;
    private SharedPreferenceManager preferenceManager;

    public MainRecipeAdapter(Context context, boolean isMain){
        this.context = context;
        this.isMain = isMain;
        preferenceManager = new SharedPreferenceManager(context);
        initAd();
    }

    public void setModelList(List<MainRecipeModel> modelList){
        this.modelList = modelList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.recipe_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainRecipeModel model = modelList.get(position);

        holder.recipeName.setText(model.getName());
        Picasso.get().load(model.getImageUrl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model2 = modelList.get(position);
                showAd();
            }
        });
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeName;
        private ImageView image;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.recipeImage);
            recipeName = itemView.findViewById(R.id.recipeName);
        }
    }

    public void initAd(){
        MobileAds.initialize(context, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                createPersonalizedAd();
            }
        });
    }
    private void createPersonalizedAd(){
        AdRequest adRequest = new AdRequest.Builder().build();

        loadInterstitialAd(adRequest);
    }

    private void loadInterstitialAd(AdRequest adRequest){
        InterstitialAd.load(context,context.getString(R.string.INTERSTITIAL_ID), adRequest, new InterstitialAdLoadCallback() {
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
                        context.startActivity(new Intent(context,SubRecipeActivity.class)
                                .putExtra("subCategoriesId",model2.getSubCategoriesId()));
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

    public void showAd(){
        previousClicked = preferenceManager.getButtonClicked();
        if (mInterstitialAd!=null && previousClicked>3) {
            preferenceManager.setButtonClicked(0);
            mInterstitialAd.show((Activity) context);
        } else {
            previousClicked = preferenceManager.getButtonClicked();
            preferenceManager.setButtonClicked(previousClicked+1);
            Log.d("Interstitial", "The interstitial ad wasn't ready yet.");
            context.startActivity(new Intent(context,SubRecipeActivity.class)
                    .putExtra("subCategoriesId",model2.getSubCategoriesId()));
        }
        Log.i("previousClicked",String.valueOf(preferenceManager.getButtonClicked()));
    }
}
