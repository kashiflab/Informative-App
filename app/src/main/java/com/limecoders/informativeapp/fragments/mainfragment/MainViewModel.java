package com.limecoders.informativeapp.fragments.mainfragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.limecoders.informativeapp.pojo.RecipeDetailsModel;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<RecipeDetailsModel>> recipeModel;
    private List<RecipeDetailsModel> model;

    public MainViewModel(){
        model = new ArrayList<>();
        recipeModel = new MutableLiveData<>();
        getRecipes();
    }

    private void getRecipes() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Recipes");

        reference.orderByChild("id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    model.add(new RecipeDetailsModel(dataSnapshot.child("id").getValue().toString(),
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
                recipeModel.setValue(model);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public MutableLiveData<List<RecipeDetailsModel>> getRecipesModel(){
        return recipeModel;
    }

}