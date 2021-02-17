package com.limecoders.informativeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.limecoders.informativeapp.fragments.listfragment.ListViewModel;
import com.limecoders.informativeapp.pojo.MainRecipeModel;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private RecyclerView recipeRecyclerView;


    private List<MainRecipeModel> models = new ArrayList<>();
    private MainRecipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("");

        Utils.initpDialog(this,"Loading...");
        Utils.showpDialog();

        recipeRecyclerView = findViewById(R.id.recipeRecyclerView);

        recipeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recipeRecyclerView.setHasFixedSize(true);
        adapter = new MainRecipeAdapter(this,false);
        recipeRecyclerView.setAdapter(adapter);

        getMainRecipes();

    }
    private void getMainRecipes() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("MainRecipes");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    models.add(new MainRecipeModel(dataSnapshot.child("id").getValue().toString(),
                            dataSnapshot.child("name").getValue().toString(),dataSnapshot.child("imageUrl").getValue().toString(),
                            dataSnapshot.child("subCategoriesId").getValue().toString()));
                }
                adapter.setModelList(models);
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
}