package com.projeto_les.easymeal.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.projeto_les.easymeal.R;

/**
 * Created by klogi on 25/12/15.
 */
public class ChipView extends FrameLayout {

    public ChipView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.chip_view, this);
    }

    public void displayItem(String text) {
        ((TextView)findViewById(R.id.chipTextView)).setText(text);
    }

}