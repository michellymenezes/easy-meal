package com.projeto_les.easymeal.adapters;


import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.models.FilterItem;

import java.util.ArrayList;
import java.util.List;

public class FilterListAdapter extends ArrayAdapter<FilterItem> {

    private static final String TAG = "Filter_list_adapter ";
    private final List<FilterItem> items;
    private final List<String> selectedItems;
    private final Activity activity;
    private List<CheckBox> checkBoxItems;
    private Button checkAllBtn;


    public FilterListAdapter(Activity activity, List<FilterItem> items, List<String> selectedItems) {
        super(activity, android.R.layout.simple_list_item_1,items );
        this.items = items;
        this.selectedItems = selectedItems;
        this.activity = activity;
        checkBoxItems = new ArrayList<>();
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

        final CheckBox checkboxItem = (CheckBox) v.findViewById(R.id.checkbox);
        final ImageButton mImgBtn = (ImageButton) v.findViewById(R.id.icon_im);
        checkboxItem.setText(currFilter.getNome());
        mImgBtn.setImageResource(currFilter.getIcon());
        if (!checkBoxItems.contains(checkboxItem))checkBoxItems.add(checkboxItem);

        setChecked();

        checkboxItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(!checkboxItem.isChecked() && selectedItems.contains(checkboxItem.getText().toString())){
                    selectedItems.remove(checkboxItem.getText().toString());
                    checkAllBtn.setText("check all");
                }
                else if(checkboxItem.isChecked() && !selectedItems.contains(checkboxItem.getText().toString())) {
                    selectedItems.add(checkboxItem.getText().toString());
                    if(allIschecked()) checkAllBtn.setText("uncheck all");
                }
            }
        });

        return v;
    }

    public List<String> getSelectedItems(){
        return selectedItems;
    }

    public void setChecked(){
        for (CheckBox cb: checkBoxItems) {
            if(selectedItems.contains(cb.getText())){
                cb.setChecked(true);
            }
            notifyDataSetChanged();
        }
    }

    public void checkAll(){
        for (CheckBox cb : checkBoxItems){
            if (!cb.isChecked()) {
                cb.setChecked(true);
                if(!selectedItems.contains(cb.getText().toString())) selectedItems.add(cb.getText().toString());
            }

        }
    }
    public void uncheckAll(){
        for (CheckBox cb : checkBoxItems){
            if (cb.isChecked()) {
                cb.setChecked(false);
                if(selectedItems.contains(cb.getText().toString())) selectedItems.remove(cb.getText().toString());
            }

        }
    }

    public boolean allIschecked() {
        return selectedItems.size() == items.size();
    }

    public void setBtnCheckall(Button checkall) {
        checkAllBtn = checkall;
    }
}