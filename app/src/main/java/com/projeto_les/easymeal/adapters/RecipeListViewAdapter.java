package com.projeto_les.easymeal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import android.widget.Toast;

import com.projeto_les.easymeal.MainActivity;

import com.projeto_les.easymeal.fragments.RecipeDetailsFragment;

import com.projeto_les.easymeal.models.RecipeItem;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;


import java.util.List;








public class RecipeListViewAdapter extends RecyclerView.Adapter {
    public static final String TAG = "RECIPE_LIST_VIEW_ADAPTER";


    private List<Recipe> items;
    private Activity activity;

    public RecipeListViewAdapter(Activity activity, List<Recipe> items) {
        this.items = items;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeItemHolder(new RecipeItem(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        View mView =  ((RecipeItem)holder.itemView);

        ((RecipeItem)holder.itemView).displayItem(items.get(position).getTitle());
        ((RecipeItem)holder.itemView).displayImage(items.get(position));

        LinearLayout ll = ((RecipeItem)holder.itemView).getLinearLayoutItem();

        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedRecipe = new Intent(view.getContext(), MainActivity.class);

                //TODO adicionar na intet o valor real de ID das receitas
                //selectedRecipe.putExtra("SELECTED_RECIPE", Integer.parseInt(id));
                ((MainActivity) activity).setmSelectedRecipeID(items.get(position).getId());
                ((MainActivity) activity).getRecipeInformation(items.get(position).getId(), false);
                ((MainActivity) activity).getInstructionsByStep(items.get(position).getId(), false);

                Toast.makeText(activity.getBaseContext(), "Wait .....  :)", Toast.LENGTH_LONG).show();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 10 seconds
                        ((MainActivity)activity).changeFragment(RecipeDetailsFragment.getInstance(),RecipeDetailsFragment.TAG,true );
                    }
                }, 5000);



            }
        });



    }

    private void removeIngredient(int position){
        items.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class RecipeItemHolder extends RecyclerView.ViewHolder {

        public RecipeItemHolder(View itemView) {
            super(itemView);
        }
    }
}



