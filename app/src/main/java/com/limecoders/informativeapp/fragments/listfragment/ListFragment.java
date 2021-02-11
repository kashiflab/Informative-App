package com.limecoders.informativeapp.fragments.listfragment;

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

public class ListFragment extends Fragment {

    private ListViewModel mViewModel;
    private RecyclerView recipeRecyclerView;


    private List<RecipeModel> models = new ArrayList<>();
    private RecipeAdapter adapter;

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        recipeRecyclerView = view.findViewById(R.id.recipeRecyclerView);

        recipeRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recipeRecyclerView.setHasFixedSize(true);
        adapter = new RecipeAdapter(getActivity(),true);
        recipeRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        // TODO: Use the ViewModel

        getRecipeData();
    }


    private void getRecipeData() {
        models.add(new RecipeModel("1","dummy1","dummy"));
        models.add(new RecipeModel("1","dummy2","dummy"));
        models.add(new RecipeModel("1","dummy3","dummy"));
        models.add(new RecipeModel("1","dummy4","dummy"));
        models.add(new RecipeModel("1","dummy5","dummy"));
        models.add(new RecipeModel("1","dummy6","dummy"));
        models.add(new RecipeModel("1","dummy7","dummy"));
        models.add(new RecipeModel("1","dummy8","dummy"));
        models.add(new RecipeModel("1","dummy9","dummy"));

        adapter.setRecipeModel(models);
    }
}