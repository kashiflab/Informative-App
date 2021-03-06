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
import com.limecoders.informativeapp.pojo.RecipeDetailsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context context;
    private List<RecipeDetailsModel> models = new ArrayList<>();
    private boolean isList;

    private RecipeDetailsModel model2;


    private int previousClicked;
    private SharedPreferenceManager preferenceManager;

    private InterstitialAd mInterstitialAd;

    public RecipeAdapter(Context context, boolean isList){
        this.context = context;
        this.isList = isList;
        preferenceManager = new SharedPreferenceManager(context);
        initAd();
    }

    public void setRecipeModel(List<RecipeDetailsModel> models){
        this.models = models;
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
        RecipeDetailsModel model = models.get(position);

        holder.recipeName.setText(model.getMainTitle());

        Picasso.get().load(model.getImageURl()).into(holder.image);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model2 = models.get(position);
                showAd();
            }
        });

//        if(model.getImageUrl().equals("dummy")) holder.image.setImageResource(R.drawable.recipe);
//
//        else Picasso.get().load(model.getImageUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return models.size();
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
                        context.startActivity(new Intent(context, RecipeDetailActivity.class)
                                .putExtra("id",model2.getId()).putExtra("imageUrl",model2.getImageURl())
                                .putExtra("mainTitle",model2.getMainTitle()).putExtra("title1",model2.getTitle1())
                                .putExtra("desc1",model2.getDesc1()).putExtra("title2",model2.getTitle2())
                                .putExtra("desc2",model2.getDesc2()).putExtra("title3",model2.getTitle3())
                                .putExtra("desc3",model2.getDesc3()).putExtra("title4",model2.getTitle4())
                                .putExtra("desc4",model2.getDesc4()).putExtra("title5",model2.getTitle5())
                                .putExtra("desc5",model2.getDesc5()));
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
            context.startActivity(new Intent(context, RecipeDetailActivity.class)
                    .putExtra("id",model2.getId()).putExtra("imageUrl",model2.getImageURl())
                    .putExtra("mainTitle",model2.getMainTitle()).putExtra("title1",model2.getTitle1())
                    .putExtra("desc1",model2.getDesc1()).putExtra("title2",model2.getTitle2())
                    .putExtra("desc2",model2.getDesc2()).putExtra("title3",model2.getTitle3())
                    .putExtra("desc3",model2.getDesc3()).putExtra("title4",model2.getTitle4())
                    .putExtra("desc4",model2.getDesc4()).putExtra("title5",model2.getTitle5())
                    .putExtra("desc5",model2.getDesc5()));
        }
        Log.i("previousClicked",String.valueOf(preferenceManager.getButtonClicked()));
    }
}
