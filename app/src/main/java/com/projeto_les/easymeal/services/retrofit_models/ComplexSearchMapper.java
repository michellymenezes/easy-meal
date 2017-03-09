package com.projeto_les.easymeal.services.retrofit_models;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.http.Query;

/**
 * Created by Caynan on 3/1/17.
 */

public class ComplexSearchMapper {

    @SerializedName("addRecipeInformation")
    private boolean addRecipeInformation;

    @SerializedName("cuisine")
    private String cuisine;

    @SerializedName("diet")
    private String diet;

    @SerializedName("excludeIngredients")
    private List<String> excludeIngredients;

    @SerializedName("fillIngredients")
    private boolean fillIngredients;

    @SerializedName("includeIngredients")
    private List<String> includeIngredients;

    @SerializedName("instructionsRequired")
    private boolean instructionsRequired;

    @SerializedName("intolerances")
    private List<String> intolerances;

    @SerializedName("limitLicense")
    private boolean limitLicense;

    @SerializedName("maxCalories")
    private Integer maxCalories;

    @SerializedName("maxCarbs")
    private Integer maxCarbs;

    @SerializedName("maxFat")
    private Integer maxFat;

    @SerializedName("maxProtein")
    private Integer maxProtein;

    @SerializedName("minCalories")
    private Integer minCalories;

    @SerializedName("minCarbs")
    private Integer minCarbs;

    @SerializedName("minFat")
    private Integer minFat;

    @SerializedName("minProtein")
    private Integer minProtein;

    @SerializedName("number")
    private Integer number;

    @SerializedName("offset")
    private Integer offset;

    @SerializedName("query")
    private String query;

    @SerializedName("ranking")
    private Integer ranking;

    @SerializedName("type")
    private String type;

    @SerializedName("recipes")
    private List<String> recipes = new ArrayList<>();


    public ComplexSearchMapper() {
    }

    public ComplexSearchMapper(boolean addRecipeInformation, String cuisine, String diet, List<String> excludeIngredients, boolean fillIngredients, List<String> includeIngredients, boolean instructionsRequired, List<String> intolerances, boolean limitLicense, Integer maxCalories, Integer maxCarbs, Integer maxFat, Integer maxProtein, Integer minCalories, Integer minCarbs,
                               Integer minFat, Integer minProtein, Integer number, Integer offset, String query, Integer ranking, String type) {
        this.addRecipeInformation = addRecipeInformation;
        this.cuisine = cuisine;
        this.diet = diet;
        setExcludeIngredients(excludeIngredients);
        this.fillIngredients = fillIngredients;
        setIncludeIngredients(includeIngredients);
        this.instructionsRequired = instructionsRequired;
        setIntolerances(intolerances);
        this.limitLicense = limitLicense;
        this.maxCalories = maxCalories;
        this.maxCarbs = maxCarbs;
        this.maxFat = maxFat;
        this.maxProtein = maxProtein;
        this.minCalories = minCalories;
        this.minCarbs = minCarbs;
        this.minFat = minFat;
        this.minProtein = minProtein;
        this.number = number;
        this.offset = offset;
        this.query = query;
        this.ranking = ranking;
        this.type = type;

    }

    public ComplexSearchMapper(List<String> recipes) {
        setRecipes(recipes);
    }

    public List<String> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<String> recipes) {
        this.recipes = recipes;
    }

    public boolean getAddRecipeInformation() {
        return addRecipeInformation;
    }

    public void setAddRecipeInformation(boolean addRecipeInformation) {
        this.addRecipeInformation = addRecipeInformation;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public List<String> getExcludeIngredients() {
        return excludeIngredients;
    }

    public void setExcludeIngredients(List<String> excludeIngredients) {
        this.excludeIngredients = excludeIngredients;
    }

    public boolean isFillIngredients() {
        return fillIngredients;
    }

    public void setFillIngredients(boolean fillIngredients) {
        this.fillIngredients = fillIngredients;
    }

    public List<String> getIncludeIngredients() {
        return includeIngredients;
    }

    public void setIncludeIngredients(List<String> includeIngredients) {
        this.includeIngredients = includeIngredients;
    }

    public boolean isInstructionsRequired() {
        return instructionsRequired;
    }

    public void setInstructionsRequired(boolean instructionsRequired) {
        this.instructionsRequired = instructionsRequired;
    }

    public List<String> getIntolerances() {
        return intolerances;
    }

    public void setIntolerances(List<String> intolerances) {

        this.intolerances = intolerances;
    }

    public boolean isLimitLicense() {
        return limitLicense;
    }

    public void setLimitLicense(boolean limitLicense) {
        this.limitLicense = limitLicense;
    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxFat() {
        return maxFat;
    }

    public void setMaxFat(Integer maxFat) {
        this.maxFat = maxFat;
    }

    public Integer getMaxProtein() {
        return maxProtein;
    }

    public void setMaxProtein(Integer maxProtein) {
        this.maxProtein = maxProtein;
    }

    public Integer getMinCalories() {
        return minCalories;
    }

    public void setMinCalories(Integer minCalories) {
        this.minCalories = minCalories;
    }

    public Integer getMinCarbs() {
        return minCarbs;
    }

    public void setMinCarbs(Integer minCarbs) {
        this.minCarbs = minCarbs;
    }

    public Integer getMinFat() {
        return minFat;
    }

    public void setMinFat(Integer minFat) {
        this.minFat = minFat;
    }

    public Integer getMinProtein() {
        return minProtein;
    }

    public void setMinProtein(Integer minProtein) {
        this.minProtein = minProtein;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecipesAsString(final String separator){
        return listToString(separator , recipes);
    }

    public String getExcludeIngredientsAsString(final String separator){
        return listToString(separator , excludeIngredients);
    }

    public String getIntolerancesAsString(final String separator){
        return listToString(separator , intolerances);
    }

    public String getIncludeIngredientsAsString(final String separator){
        return listToString(separator , includeIngredients);
    }




    @Override
    public String toString() {
        return "ComplexSearchMapper{" +
                "addRecipeInformation='" + addRecipeInformation + '\'' +
                ", cuisine='" + cuisine + '\'' +
                ", diet='" + diet + '\'' +
                ", excludeIngredients='" + excludeIngredients + '\'' +
                ", fillIngredients='" + fillIngredients + '\'' +
                ", includeIngredients='" + includeIngredients + '\'' +
                ", instructionsRequired=" + instructionsRequired +
                ", intolerances='" + intolerances + '\'' +
                ", limitLicense=" + limitLicense +
                ", maxCalories=" + maxCalories +
                ", maxCarbs=" + maxCarbs +
                ", maxFat=" + maxFat +
                ", maxProtein=" + maxProtein +
                ", minCalories=" + minCalories +
                ", minCarbs=" + minCarbs +
                ", minFat=" + minFat +
                ", minProtein='" + minProtein + '\'' +
                ", number='" + number + '\'' +
                ", offset=" + offset +
                ", query='" + query + '\'' +
                ", ranking='" + ranking + '\'' +
                ", type='" + type + '\'' +
                ", recipes=" + recipes +
                '}';
    }

    private String listToString(final String separator, final List<String> list){
        final String currentSeparator = (separator == null || separator.trim().isEmpty() ? "," : separator);
        StringBuilder stringBuilder = new StringBuilder();
        if(list == null || list.isEmpty()) {
            return stringBuilder.toString();
        }
        synchronized (list) {
            int size = list.size();
            int i;
            for (i = 0; i < size - 1; i++) {
                stringBuilder.append(list.get(i));
                stringBuilder.append(currentSeparator);
            }
            stringBuilder.append(list.get(i));
        }
        return stringBuilder.toString();
    }

}
