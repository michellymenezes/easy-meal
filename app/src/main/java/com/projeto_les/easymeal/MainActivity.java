package com.projeto_les.easymeal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.projeto_les.easymeal.fragments.InitialFragment;
import com.projeto_les.easymeal.fragments.RecipeDetailsFragment;
import com.projeto_les.easymeal.fragments.SelectIngredientsFragment;
import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructionsMapper;
import com.projeto_les.easymeal.services.retrofit_models.IngredientsMapper;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformationMapper;
import com.projeto_les.easymeal.services.retrofit_models.SpoonacularService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{

    private InitialFragment initialFragment;
    private SelectIngredientsFragment selectIngredientsFragment;
    private RecipeDetailsFragment recipeDetailsFragment;

    //Menu
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;


    public static final String TAG = "MAIN_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialFragment = InitialFragment.getInstance();
        selectIngredientsFragment = SelectIngredientsFragment.getInstance();
        recipeDetailsFragment = RecipeDetailsFragment.getInstance();

        changeFragment(initialFragment, InitialFragment.TAG, true);

        // Para iniciar o menu
        mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Para tornar o menu clicável
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);


        // end menu

        //Quando precisar iniciar a conexão a Key deve ser utilizada da seguinte maneira: getString(R.string.SPOONACULATOR_API_KEY)


        // Example of how to get information from the API
        // Here we have an example of a request to the get recipes endpoint
        // Initialize an instance of the service with our API Key, which is setted inside the file
        // gradle.properties .

        final SpoonacularService spoonacularService = new SpoonacularService(getString(R.string.SPOONACULATOR_API_KEY));

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

                   // Log.d("spoonacularService.findRecipesByIngredients", r.toString());

                    // Now it's getting the main recipe information
                    RecipeInformationMapper recipeInformationMapper = new RecipeInformationMapper(r.getId(), false);
                    spoonacularService.getRecipeInformation(recipeInformationMapper, new Callback<RecipeInformation>() {
                        @Override
                        public void onResponse(Call<RecipeInformation> call, Response<RecipeInformation> response) {
                            RecipeInformation recipeInformation = response.body();
                            Globals g = Globals.getInstance();
                            g.setRecipeInformation(recipeInformation);
                            // If everything goes right, you should see information on log
                            Log.d("spoonacularService.getRecipeInformation", recipeInformation.toString());


                        }
                        @Override
                        public void onFailure(Call<RecipeInformation> call, Throwable t) {

                        }
                    });


                    // Now you can get instructions by steps
                    AnalyzedRecipeInstructionsMapper analyzedRecipeInstructionsMapper = new AnalyzedRecipeInstructionsMapper(r.getId(), false);
                    spoonacularService.getAnalyzedRecipeInstructions(analyzedRecipeInstructionsMapper, new Callback<AnalyzedRecipeInstructions>() {
                        @Override
                        public void onResponse(Call<AnalyzedRecipeInstructions> call, Response<AnalyzedRecipeInstructions> response) {
                            AnalyzedRecipeInstructions analyzedRecipeInstructions = response.body();
                            // If everything goes right, you should see information on log
                            Log.d("spoonacularService.getAnalyzedRecipeInstructions", analyzedRecipeInstructions.toString());


                        }
                        @Override
                        public void onFailure(Call<AnalyzedRecipeInstructions> call, Throwable t) {
                            Log.d("spoonacularService.getAnalyzedRecipeInstructions", t.toString());
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                // If fail, what should we do? Handle errors and re-requests here.
                t.printStackTrace();
            }
        });

    }

    /**
     * Change the current displayed fragment by a new one.
     * - if the fragment is in backstack, it will pop it
     * - if the fragment is already displayed (trying to change the fragment with the same), it will not do anything
     *
     * @param frag            the new fragment to display
     * @param saveInBackstack if we want the fragment to be in backstack
     */
    public void changeFragment(Fragment frag, String tag, boolean saveInBackstack) {

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


    //Menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Açao para o botao de menu e voltar do menu
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    // Deve ser implementado para dar ação aos itens do menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id) {
            case R.id.nav_home_screen:
                changeFragment(InitialFragment.getInstance(),InitialFragment.TAG,true );
                break;

            case R.id.nav_ingredient:
                changeFragment(SelectIngredientsFragment.getInstance(),SelectIngredientsFragment.TAG,true );
                break;

            case R.id.nav_favorites:
               // Toast.makeText(this, getString(R.string.not_ready), Toast.LENGTH_LONG).show();
                changeFragment(RecipeDetailsFragment.getInstance(),RecipeDetailsFragment.TAG,true );//apenas para testar a tela de visualizacao da receita
                //((MainActivity)getActivity()).changeFragment();
                break;

            case R.id.nav_about:
                Toast.makeText(this, getString(R.string.not_ready), Toast.LENGTH_LONG).show();
                //((MainActivity)getActivity()).changeFragment();
                break;

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
