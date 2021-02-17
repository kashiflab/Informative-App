package com.limecoders.informativeapp.fragments.listfragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.limecoders.informativeapp.pojo.MainRecipeModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private MutableLiveData<List<MainRecipeModel>> recipeModel;
    private List<MainRecipeModel> models;

    public ListViewModel(){
        recipeModel = new MutableLiveData<>();
        models = new ArrayList<>();

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
                recipeModel.setValue(models);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public MutableLiveData<List<MainRecipeModel>> getRecipeModel() {
        return recipeModel;
    }
}