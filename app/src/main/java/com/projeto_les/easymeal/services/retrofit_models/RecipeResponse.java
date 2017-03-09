package com.projeto_les.easymeal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Caynan on 3/2/17.
 */

public class RecipeResponse {


    @SerializedName("recipe")
    private List<Recipe> recipes;

    @SerializedName("baseUri")
    private String baseUri;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("totalResults")
    private Integer totalResults;

    @SerializedName("processingTimeMs")
    private Integer processingTimeMs;


    public RecipeResponse() {
    }

    public RecipeResponse(List<Recipe> recipes, String baseUri, Integer offset, Integer totalResults, Integer processingTimeMs) {
        this.recipes = recipes;
        this.baseUri = baseUri;
        this.offset = offset;
        this.totalResults = totalResults;
        this.processingTimeMs = processingTimeMs;
    }


    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {

        this.recipes = recipes;
    }

    public String getBaseUri() {

        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }



    public Integer getProcessingTimeMs() {
        return processingTimeMs;
    }

    public void setProcessingTimeMs(Integer processingTimeMs) {
        this.processingTimeMs = processingTimeMs;
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "recipes=" + recipes +
                ", baseUri='" + baseUri + '\'' +
                ", offset=" + offset +
                ", totalResults=" + totalResults +
                ", processingTimeMs=" + processingTimeMs +
                '}';
    }
}
