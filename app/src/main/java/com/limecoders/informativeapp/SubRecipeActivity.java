package com.limecoders.informativeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.limecoders.informativeapp.pojo.MainRecipeModel;
import com.limecoders.informativeapp.pojo.RecipeDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class SubRecipeActivity extends AppCompatActivity {

    private List<RecipeDetailsModel> models = new ArrayList<>();
    private RecipeAdapter adapter;

    private RecyclerView recyclerView;

    private String subCategoriesId;
    private boolean isAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_recipe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        Utils.initpDialog(this,"Loading...");
        Utils.showpDialog();

        if(getIntent().hasExtra("isAbout")){
            isAbout = getIntent().getBooleanExtra("isAbout",false);
        }else {
            isAbout = false;
        }

        subCategoriesId = getIntent().getStringExtra("subCategoriesId");


        recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeAdapter(this,false);
        recyclerView.setAdapter(adapter);

        if(!isAbout) {
            for (int i = 0; i < subCategoriesId.split(",").length; i++) {
                getRecipeData(subCategoriesId.split(",")[i]);
            }
        }else{
            getAllRecipes();
        }
//        getRecipeData();

    }

    private void getAllRecipes() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Recipes");

        reference.orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    models.add(new RecipeDetailsModel(dataSnapshot.child("id").getValue().toString(),
                            dataSnapshot.child("imageURl").getValue().toString(),
                            dataSnapshot.child("mainTitle").getValue().toString(),
                            dataSnapshot.child("title1").getValue().toString(),
                            dataSnapshot.child("desc1").getValue().toString(),
                            dataSnapshot.child("title2").getValue().toString(),
                            dataSnapshot.child("desc2").getValue().toString(),
                            dataSnapshot.child("title3").getValue().toString(),
                            dataSnapshot.child("desc3").getValue().toString(),
                            dataSnapshot.child("title4").getValue().toString(),
                            dataSnapshot.child("desc4").getValue().toString(),
                            dataSnapshot.child("title5").getValue().toString(),
                            dataSnapshot.child("desc5").getValue().toString()
                    ));
                }
                adapter.setRecipeModel(models);
                Utils.hidepDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Utils.hidepDialog();
            }
        });
    }

    private void getRecipeData(String s) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Recipes")
                .child(s);

        reference.orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                models.add(new RecipeDetailsModel(dataSnapshot.child("id").getValue().toString(),
                        dataSnapshot.child("imageURl").getValue().toString(),
                        dataSnapshot.child("mainTitle").getValue().toString(),
                        dataSnapshot.child("title1").getValue().toString(),
                        dataSnapshot.child("desc1").getValue().toString(),
                        dataSnapshot.child("title2").getValue().toString(),
                        dataSnapshot.child("desc2").getValue().toString(),
                        dataSnapshot.child("title3").getValue().toString(),
                        dataSnapshot.child("desc3").getValue().toString(),
                        dataSnapshot.child("title4").getValue().toString(),
                        dataSnapshot.child("desc4").getValue().toString(),
                        dataSnapshot.child("title5").getValue().toString(),
                        dataSnapshot.child("desc5").getValue().toString()
                ));
                adapter.setRecipeModel(models);
                Utils.hidepDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Utils.hidepDialog();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

//    private void getRecipeData() {
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//        models.add(new MainRecipeModel("1","dummy","dummy"));
//
//        adapter.setRecipeModel(models);
//    }
}