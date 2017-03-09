package com.projeto_les.easymeal.models;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.fragments.DownloadImageTask;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;

/**
 * Created by samirsmedeiros on 09/03/17.
 */

public class RecipeItem extends FrameLayout {

    public RecipeItem(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.recipe_list_item, this);
    }

    public void displayItem(String text) {
        ((TextView)findViewById(R.id.recipe_item_name)).setText(text);
    }

    public void displayImage(Recipe recipe) {
        ImageView recipeImage = (ImageView)findViewById(R.id.recipe_image);
        new DownloadImageTask(recipeImage)
                .execute(recipe.getImage());
    }


}
