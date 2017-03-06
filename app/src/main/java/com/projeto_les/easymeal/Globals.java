package com.projeto_les.easymeal;

import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;

/**
 * Created by jairneto on 4/3/17.
 */

public class Globals{
    private static Globals instance;

    private RecipeInformation mrecipeInformation;

    public RecipeInformation getRecipeInformation() {
        return mrecipeInformation;
    }

    public void setRecipeInformation(RecipeInformation recipeInformation) {
        this.mrecipeInformation = recipeInformation;
    }

    public static synchronized Globals getInstance(){
        if(instance==null){
            instance=new Globals();
        }
        return instance;
    }
}
