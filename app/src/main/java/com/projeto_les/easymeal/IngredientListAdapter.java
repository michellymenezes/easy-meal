package com.projeto_les.easymeal;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.Route;

public class IngredientListAdapter extends ArrayAdapter<String> {


    private final ArrayList<String> ingredients;

    public IngredientListAdapter(Context context, int selected_list_item, ArrayList<String> ingredients) {
        super(context, selected_list_item);
        this.ingredients = ingredients;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);

        String ingredient = getItem(position);

        TextView titulo = (TextView) v.findViewById(R.id.selected_element);
        titulo.setText(ingredient);

        Button remove = (Button) v.findViewById(R.id.remove_ingredient);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "OPS :X", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }



}


