package com.projeto_les.easymeal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by caynan on 17/02/17.
 */
public class RecipeInformationMapper {

    @SerializedName("id")
    private int id = 0;

    @SerializedName("includeNutrition")
    private boolean includeNutrition = false;

    public RecipeInformationMapper() {}

    public RecipeInformationMapper(int id, boolean includeNutrition) {
        this.id = id;
        this.includeNutrition = includeNutrition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIncludeNutrition() {
        return includeNutrition;
    }

    public void setIncludeNutrition(boolean includeNutrition) {
        this.includeNutrition = includeNutrition;
    }

    @Override
    public String toString() {
        return "RecipeInformationMapper{" +
                "id=" + id +
                ", includeNutrition=" + includeNutrition +
                '}';
    }

}
