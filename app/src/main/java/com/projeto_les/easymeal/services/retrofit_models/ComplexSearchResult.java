package com.projeto_les.easymeal.services.retrofit_models;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * Created by michelly on 10/03/17.
 */

public class ComplexSearchResult {

    @SerializedName("results")
    private List<Recipe> results;

    public ComplexSearchResult() {}

    public ComplexSearchResult(List<Recipe> results) {
        this.results = results;
    }

    public List<Recipe> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ComplexSearchResult{" +
                "results=" + results +
                '}';
    }
}
