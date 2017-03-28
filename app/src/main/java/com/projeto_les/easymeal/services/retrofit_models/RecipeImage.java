package com.projeto_les.easymeal.services.retrofit_models;


import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

public class RecipeImage {
    private Recipe recipe;
    private Bitmap  bitmap;

    public RecipeImage(Recipe recipe, Bitmap bitmap) {
        this.recipe = recipe;
        this.bitmap = bitmap;
    }

    public RecipeImage(Recipe recipe){
        this.recipe = recipe;
        bitmap = null;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setImageView(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
