package com.projeto_les.easymeal;

import android.util.Log;

import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;

/**
 * Created by jairneto on 4/3/17.
 */

public class Globals{
    private static Globals instance;

    private RecipeInformation mrecipeInformation;
    private AnalyzedRecipeInstructions mAnalyzedRecipeInstructions;

    public RecipeInformation getRecipeInformation() {
        return mrecipeInformation;
    }

    public void setRecipeInformation(RecipeInformation recipeInformation) {
        Log.d("Globals", "Recipe information - " + recipeInformation.getTitle());
        this.mrecipeInformation = recipeInformation;
    }

    public AnalyzedRecipeInstructions getAnalyzedRecipeInstructions() {return mAnalyzedRecipeInstructions;}

    public void setmAnalyzedRecipeInstructions(AnalyzedRecipeInstructions analyzedRecipeInstructions) {
        Log.d("Globals","Analyzed Recipe Instruction" + analyzedRecipeInstructions.getName());
        this.mAnalyzedRecipeInstructions = analyzedRecipeInstructions;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
