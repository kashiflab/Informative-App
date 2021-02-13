package com.limecoders.informativeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.limecoders.informativeapp.pojo.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class SubRecipeActivity extends AppCompatActivity {

    private List<RecipeModel> models = new ArrayList<>();
    private RecipeAdapter adapter;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_recipe);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        recyclerView = findViewById(R.id.recipeRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setHasFixedSize(true);

        adapter = new RecipeAdapter(this,false);
        recyclerView.setAdapter(adapter);
        getRecipeData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void getRecipeData() {
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));

        adapter.setRecipeModel(models);
    }
}