package com.projeto_les.easymeal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;

import java.util.ArrayList;
import java.util.List;


public class RecipeListViewAdapter extends ArrayAdapter {

    private List<String[]> items;
    private Activity activity;

    public RecipeListViewAdapter(Activity activity, List<String[]> items) {
        super(activity, android.R.layout.simple_list_item_1,items );

        this.items = items;
        this.activity = activity;
    }

    @Override
    public String[] getItem(int position) {
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

        final String name = items.get(position)[2];

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, null);
        }

        TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_item_name);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipe_image);

        recipeName.setText(name);
        //recipeImage.setImageDrawable();

        recipeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedRecipe = new Intent(view.getContext(), MainActivity.class);
                //TODO adicionar na intet o valor real de ID das receitas
                selectedRecipe.putExtra("SELECTED_RECIPE", getItemId(position));
                //((MainActivity) activity).viewReceitaSelecionada(getContext(),currItem);
                //((MainActivity) activity).onRecipePressed(view);
            }
        });



        return convertView;
    }


}
