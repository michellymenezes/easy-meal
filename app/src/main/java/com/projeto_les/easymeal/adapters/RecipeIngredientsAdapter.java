package com.projeto_les.easymeal.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.models.ChipView;
import com.projeto_les.easymeal.models.ChipViewShow;

import java.util.List;

/**
 * Created by samirsmedeiros on 06/03/17.
 */

public class RecipeIngredientsAdapter extends RecyclerView.Adapter {

    private List<String> items;
    private List<String> itemsImg;

    public RecipeIngredientsAdapter(List<String> items, List<String> itemsImg) {
        this.items = items;
        this.itemsImg = itemsImg;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChipViewHolder(new ChipViewShow(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        View mView =  ((ChipViewShow)holder.itemView);
        ((ChipViewShow)holder.itemView).displayItem(items.get(position));
        ((ChipViewShow)holder.itemView).displayImg(itemsImg.get(position));




    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    private class ChipViewHolder extends RecyclerView.ViewHolder {

        public ChipViewHolder(View itemView) {
            super(itemView);
        }
    }
}


