package com.projeto_les.easymeal.adapters;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.models.FilterItem;

import java.util.List;

public class FilterListAdapter extends ArrayAdapter<FilterItem> {

    private static final String TAG = "Filter_list_adapter ";
    private final List<FilterItem> items;
    private final List<String> selectedItems;
    private final Activity activity;


    public FilterListAdapter(Activity activity, List<FilterItem> items, List<String> selectedItems) {
        super(activity, android.R.layout.simple_list_item_1,items );
        this.items = items;
        this.selectedItems = selectedItems;
        this.activity = activity;
    }

    @Override
    public FilterItem getItem(int position) {
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        final FilterItem currFilter = items.get(position);

        if (v == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            v = inflater.inflate(R.layout.filter_list_item, null);
        }

        final ImageView imViewItem = (ImageView) v.findViewById(R.id.icon_filter_list_itenm);
        final CheckBox checkboxItem = (CheckBox) v.findViewById(R.id.checkbox);
        final TextView checkboxName = (TextView) v.findViewById(R.id.checkbox_name);
        checkboxName.setText(currFilter.getNome());
        imViewItem.setImageResource(currFilter.getIcon());

        checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!checkboxItem.isChecked() && selectedItems.contains(checkboxName.getText().toString())){
                    selectedItems.remove(checkboxName.getText().toString());
                }
                else if(checkboxItem.isChecked() && !selectedItems.contains(checkboxName.getText().toString())) {
                    selectedItems.add(checkboxName.getText().toString());
                }
            }
        });

        return v;
    }

     public List<String> getSelectedItems(){
         return selectedItems;
     }

}