package com.projeto_les.easymeal;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.projeto_les.easymeal.services.retrofit_models.IngredientsMapper;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.SpoonacularService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Quando precisar iniciar a conexão a Key deve ser utilizada da seguinte maneira: getString(R.string.SPOONACULATOR_API_KEY)


        // Example of how to get information from the API
        // Here we have an example of a request to the get recipes endpoint
        // Initialize an instance of the service with our API Key, which is setted inside the file
        // gradle.properties .

        SpoonacularService spoonacularService = new SpoonacularService(getString(R.string.SPOONACULATOR_API_KEY));

        // Parameters of the request, we're using an object to encapsulate them.
        IngredientsMapper ingredientsMapper = new IngredientsMapper();
        ingredientsMapper.setFillIngredients(false);
        ingredientsMapper.setLimitLicense(false);
        ingredientsMapper.setNumber(5);
        ingredientsMapper.setRanking(1);

        List<String> ingredients = new ArrayList<>(Arrays.asList("sugar", "flour", "apples"));
        ingredientsMapper.setIngredients(ingredients);

        // pass the mapper object and a callback (the request is async)
        spoonacularService.findRecipesByIngredients(ingredientsMapper, new Callback<List<Recipe>>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                // If everything goes right, you should receive a List of Recipe objects.
                List<Recipe> recipes = response.body();

                for(Recipe r : recipes) {
                    Log.d("spoonacularService.findRecipesByIngredients", r.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                // If fail, what should we do? Handle errors and re-requests here.
                t.printStackTrace();
            }
        });



    }
}
