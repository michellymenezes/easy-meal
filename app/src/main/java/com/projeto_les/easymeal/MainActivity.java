package com.projeto_les.easymeal;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.projeto_les.easymeal.services.retrofit_models.IngredientsMapper;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.SpoonacularService;

import com.projeto_les.easymeal.fragments.InitialFragment;
import com.projeto_les.easymeal.fragments.SelectIngredientsFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private InitialFragment initialFragment;
    private SelectIngredientsFragment selectIngredientsFragment;

    public static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        initialFragment = InitialFragment.getInstance();
        selectIngredientsFragment = SelectIngredientsFragment.getInstance();

        changeFragment(initialFragment, InitialFragment.TAG, true);


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

                for (Recipe r : recipes) {

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

    private void changeFragment(Fragment frag, String tag, boolean saveInBackstack) {


        try {
            FragmentManager manager = getSupportFragmentManager();
            //fragment not in back stack, create it.
            FragmentTransaction transaction = manager.beginTransaction();


            transaction.replace(R.id.content_layout, frag, tag);

            if (saveInBackstack) {
                Log.d(TAG, "Change Fragment: addToBackTack " + tag);
                transaction.addToBackStack(tag);
            } else {
                Log.d(TAG, "Change Fragment: NO addToBackTack");
            }
            transaction.commit();
            // custom effect if fragment is already instanciated

        } catch (IllegalStateException exception) {
            Log.w(TAG, "Unable to commit fragment, could be activity as been killed in background. " + exception.toString());
        }
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
            return;
        }

        super.onBackPressed();
    }


    public void onSelectIngredientsButtonPressed(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,
                selectIngredientsFragment, SelectIngredientsFragment.TAG).addToBackStack(SelectIngredientsFragment.TAG).commit();
    }
}
