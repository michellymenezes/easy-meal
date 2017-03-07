package com.projeto_les.easymeal.adapters;

import android.app.Activity;
import android.database.DataSetObserver;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;

import java.util.ArrayList;
import java.util.List;


public class RecipeListViewAdapter extends ArrayAdapter {

    private List<String> items;
    private Activity activity;

    public RecipeListViewAdapter(Activity activity, List<String> items) {
        super(activity, android.R.layout.simple_list_item_1,items );

        this.items = items;
        this.activity = activity;
    }

    @Override
    public String getItem(int position) {
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

        final String name = items.get(position);

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, null);
        }

        TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_item_name);
        TextView numberIngr = (TextView) convertView.findViewById(R.id.have);

        recipeName.setText(name);
        //numberIngr.setText();

        recipeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //((MainActivity) activity).viewReceitaSelecionada(getContext(),currItem);
                //((MainActivity) activity).onRecipePressed(view);
            }
        });



        return convertView;
    }


}
