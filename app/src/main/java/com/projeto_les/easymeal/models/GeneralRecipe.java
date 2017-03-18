package com.projeto_les.easymeal.models;

import android.graphics.Bitmap;

import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;

public class GeneralRecipe {
    private Recipe recipe;
    private Bitmap image;
    private RecipeInformation recipeInformation;
    private AnalyzedRecipeInstructions analyzedRecipeInstructions;

    public GeneralRecipe(Recipe recipe) {
        this.recipe = recipe;
        this.image = null;
        this.recipeInformation = null;
        this.analyzedRecipeInstructions = null;
    }

    public GeneralRecipe(Recipe recipe, Bitmap image) {
        this.recipe = recipe;
        this.image = image;
        this.recipeInformation = null;
        this.analyzedRecipeInstructions = null;
    }


    public GeneralRecipe(Recipe recipe, Bitmap image, RecipeInformation recipeInformation) {
        this.recipe = recipe;
        this.image = image;
        this.recipeInformation = recipeInformation;
        this.analyzedRecipeInstructions = null;
    }


    public GeneralRecipe(Recipe recipe, Bitmap image, RecipeInformation recipeInformation, AnalyzedRecipeInstructions analyzedRecipeInstructions) {
        this.recipe = recipe;
        this.image = image;
        this.recipeInformation = recipeInformation;
        this.analyzedRecipeInstructions = analyzedRecipeInstructions;
    }


    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public RecipeInformation getRecipeInformation() {
        return recipeInformation;
    }

    public void setRecipeInformation(RecipeInformation recipeInformation) {
        this.recipeInformation = recipeInformation;
    }

    public AnalyzedRecipeInstructions getAnalyzedRecipeInstructions() {
        return analyzedRecipeInstructions;
    }

    public void setAnalyzedRecipeInstructions(AnalyzedRecipeInstructions analyzedRecipeInstructions) {
        this.analyzedRecipeInstructions = analyzedRecipeInstructions;
    }
}
