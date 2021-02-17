package com.limecoders.informativeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.limecoders.informativeapp.databinding.ActivityMainBinding;
import com.limecoders.informativeapp.databinding.ActivityRecipeDetailBinding;
import com.squareup.picasso.Picasso;

public class RecipeDetailActivity extends AppCompatActivity {

    ActivityRecipeDetailBinding binding;

    private String id, mainTitle ="", imageUrl="", title1="", desc1="", title2="", desc2="", title3="",
            desc3="", title4="", desc4="",
    title5="", desc5="";

    private boolean notiIntent = false;
    private String recipeId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        if(getIntent().hasExtra("notiIntent")){
            notiIntent = getIntent().getBooleanExtra("notiIntent",false);
            recipeId = getIntent().getStringExtra("recipeId");
        }

        if(notiIntent){
            Utils.initpDialog(this,"Loading...");
            Utils.showpDialog();
            getRecipeDetail();
        }else {
            id = getIntent().getStringExtra("id");
            mainTitle = getIntent().getStringExtra("mainTitle");
            title1 = getIntent().getStringExtra("title1");
            desc1 = getIntent().getStringExtra("desc1");
            title2 = getIntent().getStringExtra("title2");
            desc2 = getIntent().getStringExtra("desc2");
            title3 = getIntent().getStringExtra("title3");
            desc3 = getIntent().getStringExtra("desc3");
            title4 = getIntent().getStringExtra("title4");
            desc4 = getIntent().getStringExtra("desc4");
            title5 = getIntent().getStringExtra("title5");
            desc5 = getIntent().getStringExtra("desc5");
            imageUrl = getIntent().getStringExtra("imageUrl");
            setData();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");


    }

    private void getRecipeDetail() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Recipes");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    if(dataSnapshot.child("id").getValue().toString().equals(recipeId)) {
                        imageUrl = dataSnapshot.child("imageURl").getValue().toString();
                        mainTitle = dataSnapshot.child("mainTitle").getValue().toString();
                        title1 = dataSnapshot.child("title1").getValue().toString();
                        desc1 = dataSnapshot.child("desc1").getValue().toString();
                        title2 = dataSnapshot.child("title2").getValue().toString();
                        desc2 = dataSnapshot.child("desc2").getValue().toString();
                        title3 = dataSnapshot.child("title3").getValue().toString();
                        desc3 = dataSnapshot.child("desc3").getValue().toString();
                        title4 = dataSnapshot.child("title4").getValue().toString();
                        desc4 = dataSnapshot.child("desc4").getValue().toString();
                        title5 = dataSnapshot.child("title5").getValue().toString();
                        desc5 = dataSnapshot.child("desc5").getValue().toString();

                        setData();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Utils.hidepDialog();
            }
        });
    }

    private void setData() {
        Picasso.get().load(imageUrl).into(binding.recipeImage);

        if(!title3.equals("")){
            binding.title3.setVisibility(View.VISIBLE);
            binding.desc3.setVisibility(View.VISIBLE);
        }
        if(!title4.equals("")){
            binding.title4.setVisibility(View.VISIBLE);
            binding.desc4.setVisibility(View.VISIBLE);
        }
        if(!title5.equals("") ){
            binding.title5.setVisibility(View.VISIBLE);
            binding.desc5.setVisibility(View.VISIBLE);
        }

        binding.mainTitle.setText(mainTitle);
        binding.title1.setText(title1);
        binding.desc1.setText(desc1);
        binding.title2.setText(title2);
        binding.desc2.setText(desc2);
        binding.title3.setText(title3);
        binding.desc3.setText(desc3);
        binding.title4.setText(title4);
        binding.desc4.setText(desc4);
        binding.title5.setText(title5);
        binding.desc5.setText(desc5);
        Utils.hidepDialog();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigateUp() {
        onBackPressed();
        return super.onNavigateUp();
    }
}