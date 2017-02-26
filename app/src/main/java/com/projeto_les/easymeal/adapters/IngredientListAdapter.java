package com.projeto_les.easymeal.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;


import android.widget.ImageButton;
import android.widget.TextView;

import com.projeto_les.easymeal.R;

import java.util.List;

public class IngredientListAdapter extends BaseAdapter {

    private List<String> items;
    private Activity activity;

    public IngredientListAdapter(Activity activity, List<String> items) {

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

        String currItem = items.get(position);
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.chip_view, null);
        }

        TextView listItem = (TextView) convertView.findViewById(R.id.chipTextView);
        listItem.setText(currItem);

        ImageButton removeIngredientButton = (ImageButton) convertView.findViewById(R.id.view_chip_close_button);

        removeIngredientButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeIngredient(position);
            }
        });

        return convertView;
    }

    private void removeIngredient(int position){
        items.remove(position);
        notifyDataSetChanged();
    }

}