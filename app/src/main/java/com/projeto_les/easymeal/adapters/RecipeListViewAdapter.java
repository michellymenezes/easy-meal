package com.projeto_les.easymeal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import com.projeto_les.easymeal.MainActivity;

import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.fragments.DownloadImageTask;
import com.projeto_les.easymeal.fragments.RecipeDetailsFragment;

import com.projeto_les.easymeal.models.GeneralRecipe;
import com.projeto_les.easymeal.models.RecipeItem;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;


import java.util.List;

public class RecipeListViewAdapter extends ArrayAdapter {
    public static final String TAG = "RECIPE_LIST_VIEW_ADAPTER";


    private List<GeneralRecipe> items;
    private Activity activity;

    public RecipeListViewAdapter(Activity activity, List<GeneralRecipe> items) {
        super(activity, android.R.layout.simple_list_item_1,items );

        this.items = items;
        this.activity = activity;
    }

    @Override
    public GeneralRecipe getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount(){
        return items.size();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final int id = items.get(position).getRecipe().getId();
        final String name = items.get(position).getRecipe().getTitle();
        final String image = items.get(position).getRecipe().getImage();

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, null);
        }

        TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_item_name);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipe_image);

        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.recipe_ll);

        new DownloadImageTask(recipeImage)
                .execute(items.get(position).getRecipe().getImage());

        recipeName.setText(name);



        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO adicionar na intet o valor real de ID das receitas
                //selectedRecipe.putExtra("SELECTED_RECIPE", Integer.parseInt(id));
                ((MainActivity) activity).setmSelectedRecipeID(items.get(position).getRecipe().getId());
                ((MainActivity) activity).getRecipeInformation(items.get(position).getRecipe().getId(), false);

                //Nesse aqui tem o change
                ((MainActivity) activity).getInstructionsByStep(items.get(position).getRecipe().getId(), false);

                Toast.makeText(getContext(), R.string.wait, Toast.LENGTH_LONG).show();


            }
        });



        return convertView;
    }

}

