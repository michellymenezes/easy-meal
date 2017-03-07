package com.projeto_les.easymeal.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.projeto_les.easymeal.R;


public class ChipViewShow extends FrameLayout {

    public ChipViewShow(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.chip_view_show, this);
    }

    public void displayItem(String text) {
        ((TextView)findViewById(R.id.chipTextViewShow)).setText(text);
    }


}