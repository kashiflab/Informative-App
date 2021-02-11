package com.limecoders.informativeapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private Context context;
    private List<RecipeModel> models = new ArrayList<>();
    private boolean isList;

    public RecipeAdapter(Context context, boolean isList){
        this.context = context;
        this.isList = isList;
    }

    public void setRecipeModel(List<RecipeModel> models){
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
        RecipeModel model = models.get(position);

        holder.recipeName.setText(model.getName());

        if(position%2==0){
            holder.image.setImageResource(R.drawable.recipe);
        }else {
            holder.image.setImageResource(R.drawable.recipe2);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isList) {
                    context.startActivity(new Intent(context, RecipeDetailActivity.class)
                            .putExtra("id", model.getId()));
                }else{
                    context.startActivity(new Intent(context, SubRecipeActivity.class)
                            .putExtra("id", model.getId()));
                }
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
}
