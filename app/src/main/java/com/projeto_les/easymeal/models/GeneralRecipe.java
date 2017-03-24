package com.projeto_les.easymeal.models;

import android.graphics.Bitmap;

import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;

public class GeneralRecipe implements Comparable {
    private Recipe recipe;
    private RecipeInformation recipeInformation;
    private AnalyzedRecipeInstructions analyzedRecipeInstructions;
    private Bitmap image;
    private Integer missedIngredientCount;

    public GeneralRecipe(Recipe recipe) {
        this.recipe = recipe;
        this.image = null;
        recipeInformation = null;
        analyzedRecipeInstructions = null;
        this.missedIngredientCount = recipe.getMissedIngredientCount();

    }

    public GeneralRecipe(Recipe recipe, Bitmap image) {
        this.recipe = recipe;
        this.image = image;
        recipeInformation = null;
        analyzedRecipeInstructions = null;

    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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

    public Integer getMissedIngredientCount() {return this.missedIngredientCount;}

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public int compareTo(Object o) {
        GeneralRecipe recipe = (GeneralRecipe) o;
        return this.getMissedIngredientCount() - recipe.getMissedIngredientCount();
    }
}
