package com.limecoders.informativeapp.fragments.mainfragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.limecoders.informativeapp.R;
import com.limecoders.informativeapp.RecipeAdapter;
import com.limecoders.informativeapp.RecipeModel;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private RecyclerView recipeRecyclerView;


    private List<RecipeModel> models = new ArrayList<>();
    private RecipeAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView);

        recipeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recipeRecyclerView.setHasFixedSize(true);
        adapter = new RecipeAdapter(getActivity(),false);
        recipeRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        getRecipeData();
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
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
        models.add(new RecipeModel("1","dummy","dummy"));
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