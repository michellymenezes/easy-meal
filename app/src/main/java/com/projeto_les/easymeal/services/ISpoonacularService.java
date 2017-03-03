package com.projeto_les.easymeal.services;

import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.projeto_les.easymeal.services.retrofit_models.GroceryProducts;
import com.projeto_les.easymeal.services.retrofit_models.GroceryProductsMapper;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ISpoonacularService {

    /**
     * Describes header for the Map Ingredients to Grocery Products endpoint
     *
     * @param mashapeKey
     * @param contentType
     * @param accept
     * @param body  object that represents the body object, retrofit do the unpacking of this object.
     * @return  a list of GroceryProducts
     */
    @POST("food/ingredients/map")
    Call<List<GroceryProducts>> groceryProducts(
            @Header("X-Mashape-Key") String mashapeKey,
            @Header("Content-Type") String contentType,
            @Header("Accept") String accept,
            @Body GroceryProductsMapper body);

    /**
     * Describes request for 'Find By Ingredients' endpoint
     * Find recipes that use as many of the given ingredients as possible and have as little as possible missing ingredients.
     *
     * @param mashapeKey
     * @param contentType
     * @param accept
     * @param fillIngredients   Add information about the used and missing ingredients in each recipe.
     * @param ingredients   A comma-separated list of ingredients that the recipes should contain.
     * @param limitLicense  Whether to only show recipes with an attribution license.
     * @param number    The maximal number of recipes to return
     * @param ranking   Whether to maximize used ingredients (1) or minimize missing ingredients (2) first.
     * @return  A List of Recipe objects
     */
    @GET("recipes/findByIngredients")
    Call<List<Recipe>> findRecipesByIngredients(
            @Header("X-Mashape-Key") String mashapeKey,
            @Header("Content-Type") String contentType,
            @Header("Accept") String accept,
            @Query("fillIngredients") boolean fillIngredients,
            @Query("ingredients") String ingredients,
            @Query("limitLicense") boolean limitLicense,
            @Query("number") Integer number,
            @Query("ranking") Integer ranking);

    /**
     * Describe request for 'Get Recipe Information' endpoint
     *
     * @param mashapeKey
     * @param contentType
     * @param accept
     * @param id    The id of the recipe.
     * @param includeNutrition  Include nutrition data to the recipe information.
     * @return  RecipeInformation object with response
     */
    @GET("recipes/{id}/information")
    Call<RecipeInformation> getRecipeInformation(
            @Header("X-Mashape-Key") String mashapeKey,
            @Header("Content-Type") String contentType,
            @Header("Accept") String accept,
            @Path("id") int id,
            @Query("includeNutrition") boolean includeNutrition
    );


    /**
     * Describe request for 'Get Recipe Information' endpoint
     *
     * @param mashapeKey
     * @param contentType
     * @param accept
     * @param id    The id of the recipe.
     * @param stepBreakdown  Whether to break down the recipe steps even more.
     * @return  AnalyzedRecipeInstructions object with response
     */
    @GET("recipes/{id}/analyzedInstructions")
    Call<AnalyzedRecipeInstructions> getAnalyzedRecipeInstructions(
            @Header("X-Mashape-Key") String mashapeKey,
            @Header("Content-Type") String contentType,
            @Header("Accept") String accept,
            @Path("id") int id,
            @Query("stepBreakdown") boolean stepBreakdown
    );
}
