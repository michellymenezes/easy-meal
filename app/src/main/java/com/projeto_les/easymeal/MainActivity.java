package com.projeto_les.easymeal;

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

import com.projeto_les.easymeal.fragments.RecipeDetailsFragment;
import com.projeto_les.easymeal.fragments.RecipesListFragment;
import com.projeto_les.easymeal.fragments.SelectIngredientsFragment;
import com.projeto_les.easymeal.fragments.TutorialFragment;
import com.projeto_les.easymeal.models.GeneralRecipe;
import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructions;
import com.projeto_les.easymeal.services.retrofit_models.AnalyzedRecipeInstructionsMapper;
import com.projeto_les.easymeal.services.retrofit_models.ComplexSearchMapper;
import com.projeto_les.easymeal.services.retrofit_models.ComplexSearchResult;
import com.projeto_les.easymeal.services.retrofit_models.IngredientsMapper;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformationMapper;
import com.projeto_les.easymeal.services.retrofit_models.SpoonacularService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements  NavigationView.OnNavigationItemSelectedListener{


    private SelectIngredientsFragment selectIngredientsFragment;
    private RecipeDetailsFragment recipeDetailsFragment;
    private RecipesListFragment listRecipesFragment;

    //Menu
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolbar;
    private NavigationView mNavigationView;

    private SpoonacularService spoonacularService;
    public static final String TAG = "MAIN_ACTIVITY";

    private List<String> mSelectedFilters;
    private List<String> mSelectedCuisines;
    private List<String> mSelectedDiets;

    private List<String> mSelectedIngredients;

    private List<GeneralRecipe> generalRecipes;
    private GeneralRecipe generalRecipeSelected;
    private Globals g;
    //private  List<Recipe> recipes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectIngredientsFragment = SelectIngredientsFragment.getInstance();
        recipeDetailsFragment = RecipeDetailsFragment.getInstance();
        listRecipesFragment = RecipesListFragment.getInstance();

        g = Globals.getInstance();

        mSelectedFilters = new ArrayList<>();
        mSelectedDiets = new ArrayList<>();
        mSelectedCuisines = new ArrayList<>();

        mSelectedIngredients = new ArrayList<>();
        generalRecipes = new ArrayList<>();
        generalRecipeSelected = null;

        changeFragment(selectIngredientsFragment,SelectIngredientsFragment.TAG,true );


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

        spoonacularService = new SpoonacularService(getString(R.string.SPOONACULATOR_API_KEY));


        // Parameters of the request, we're using an object to encapsulate them.
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

    public void inicializeSpoonacularService(){

        IngredientsMapper ingredientsMapper = new IngredientsMapper();
        ingredientsMapper.setFillIngredients(false);
        ingredientsMapper.setLimitLicense(false);
        ingredientsMapper.setNumber(5);
        ingredientsMapper.setRanking(1);
        List<String> ingredients = getSelectedIngredients();
        ingredientsMapper.setIngredients(ingredients);

      //######################################################################################

      //COMPLEX SEARCH - FUNCIONANDO
      //
      //      coloquei apenas os atributos que interessam a nossa pesquisa:
      //          em ordem são:
      //          String cuisine: String para filtro de cozinha separado por virgula
      //          String diet: String para filtro de dieta separado por virgula
      //          String includeIngredients: String para filtro de ingredientes separado por virgula
      //          String intolerances: String para filtro de intoletancias separado por virgula
      //          Integer number: quantidade de receitas que deve retornar (obrigatório)
      //          String query: uma palavra pra epscificar o tipo de comida (macarronada, carne...) (obrigatorio)
      //          Integer ranking: ordenar receitas, o valor pode ser 1 ou 2. Whether to maximize used ingredients (1) or
      //                          minimize missing ingredients (2) first (obrigatório)
      //          String type: String para filtro de tipo de refeição separado por virgula



        //ComplexSearchMapper complexSearchMapper1 = new ComplexSearchMapper(null, "vegan", "beans,bacon", null, 5, "beans", 1, null);

        String query = "";
        if (mSelectedFilters != null && mSelectedFilters.size()>0){
            query = mSelectedFilters.get(0);
        } else if (mSelectedIngredients != null && mSelectedIngredients.size() >0){
            query = mSelectedIngredients.get(0);
        }

        generalRecipes.clear();

        ComplexSearchMapper complexSearchMapper = new ComplexSearchMapper(getStringSelectedCuisines(),getStringSelectedDiets(),getStringSelectedIngredients(), null, 5,null,1, getStringSelectedFilters());
        spoonacularService.searchComplex(complexSearchMapper, new Callback<ComplexSearchResult>() {
            @Override
            public void onResponse(Call<ComplexSearchResult> call, Response<ComplexSearchResult> response) {

                ComplexSearchResult result = response.body();
                //Aqui é retornada uma lista com os objetos de receitas
                //recipes = result.getResults();

                int i = 1;
                for(Recipe recipe : result.getResults()){
                    Log.d("COMPLEX_SEARCH-RECIPE "+i, recipe.toString());
                    i++;
                    generalRecipes.add(new GeneralRecipe(recipe));
                }

                changeFragment(RecipesListFragment.getInstance(), RecipesListFragment.TAG,true );
            }

            @Override
            public void onFailure(Call<ComplexSearchResult> call, Throwable t) {

            }
        });

    }

    private void clearSearch() {
        mSelectedIngredients.clear();
        mSelectedFilters.clear();
        mSelectedCuisines.clear();
        mSelectedDiets.clear();
    }

    //public List<Recipe> getRecipes(){

    //return recipes;
    //}

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
            return;
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        String fragmentTag = fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 1).getName();

        if (fragmentTag.equals(SelectIngredientsFragment.TAG)) {
            selectIngredientsFragment.updateView();
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

            case R.id.nav_ingredient:
                changeFragment(SelectIngredientsFragment.getInstance(), SelectIngredientsFragment.TAG, true );
                clearSearch();
                break;

            case R.id.nav_favorites:
                // Toast.makeText(this, getString(R.string.not_ready), Toast.LENGTH_LONG).show();
                changeFragment(RecipeDetailsFragment.getInstance(), RecipeDetailsFragment.TAG, true );//apenas para testar a tela de visualizacao da receita
                //((MainActivity)getActivity()).changeFragment();
                break;

            case R.id.nav_tutorial:
                changeFragment(TutorialFragment.getInstance(), TutorialFragment.TAG, true );
                break;

            case R.id.nav_about:
                Toast.makeText(this, getString(R.string.not_ready), Toast.LENGTH_LONG).show();
                //((MainActivity)getActivity()).changeFragment();
                break;

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    public List<String> getSelectedFilters() {
        return mSelectedFilters;
    }

    public List<String> getSelectedCuisines() {
        return mSelectedCuisines;
    }

    public List<String> getSelectedDiets() {
        return mSelectedDiets;
    }

    public List<String> getSelectedIngredients() {
        return mSelectedIngredients;
    }

    public void setSelectedFilters(List<String> selectedFilters) {
        this.mSelectedFilters = selectedFilters;
    }

    public void setSelectedCuisines(List<String> selectedCuisines) {
        this.mSelectedCuisines = selectedCuisines;
    }

    public void setSelectedDiets(List<String> selectedDiets) {
        this.mSelectedDiets = selectedDiets;
    }

    public void setSelectedIngredients(List<String> selectedIngredients) {
        this.mSelectedIngredients = selectedIngredients;
    }

    public SpoonacularService getSpoonacularService() {
        if (spoonacularService==null){
            spoonacularService = new SpoonacularService(getString(R.string.SPOONACULATOR_API_KEY));
        }
        return spoonacularService;
    }

    private String getStringSelectedIngredients(){
        String ingredients = "";

        if (mSelectedIngredients!= null){
            for (String string : mSelectedIngredients){
                ingredients += string + ",";
            }

            if (ingredients.endsWith(",")){
                ingredients =ingredients.substring(0, ingredients.length()-1);
            }
        }
        return ingredients;
    }

    private String getStringSelectedFilters(){
        String filters = "";

        if (mSelectedFilters!= null){
            for (String string : mSelectedFilters){
                filters += string + ",";
            }

            if (filters.endsWith(",")){
                filters =filters.substring(0, filters.length()-1);
            }
        }

        return filters;
    }

    private String getStringSelectedCuisines(){
        String cuisines = "";

        if (mSelectedCuisines!= null){
            for (String string : mSelectedCuisines){
                cuisines += string + ",";
            }

            if (cuisines.endsWith(",")){
                cuisines =cuisines.substring(0, cuisines.length()-1);
            }
        }

        return cuisines;
    }

    private String getStringSelectedDiets(){
        String diets = "";

        if (mSelectedDiets!= null){
            for (String string : mSelectedDiets){
                diets += string + ",";
            }

            if (diets.endsWith(",")){
                diets =diets.substring(0, diets.length()-1);
            }
        }

        return diets;
    }


    public void getRecipeInformation(final int id, Boolean includeNutrition){
        // Now it's getting the main recipe information
        RecipeInformationMapper recipeInformationMapper = new RecipeInformationMapper(id, includeNutrition);
        spoonacularService.getRecipeInformation(recipeInformationMapper, new Callback<RecipeInformation>() {
            @Override
            public void onResponse(Call<RecipeInformation> call, Response<RecipeInformation> response) {
                RecipeInformation recipeInformation = response.body();
                g.setRecipeInformation(recipeInformation);
                if (generalRecipeSelected != null){
                    generalRecipeSelected.setRecipeInformation(recipeInformation);
                }
                // If everything goes right, you should see information on log
                Log.d("getRecipeInformation", recipeInformation.toString());

                //Nesse aqui tem o change
                getInstructionsByStep(id, false);

            }
            @Override
            public void onFailure(Call<RecipeInformation> call, Throwable t) {

            }
        });
    }

    public void getInstructionsByStep(int id, boolean stepBreakdown) {

        // Now you can get instructions by steps
        AnalyzedRecipeInstructionsMapper analyzedRecipeInstructionsMapper = new AnalyzedRecipeInstructionsMapper(id, stepBreakdown);
        spoonacularService.getAnalyzedRecipeInstructions(analyzedRecipeInstructionsMapper, new Callback<List<AnalyzedRecipeInstructions>>() {
            @Override
            public void onResponse(Call<List<AnalyzedRecipeInstructions>> call, Response<List<AnalyzedRecipeInstructions>> response) {
                List<AnalyzedRecipeInstructions> analyzedRecipeInstructions = response.body();

                for (AnalyzedRecipeInstructions i : analyzedRecipeInstructions) {
                    g.setmAnalyzedRecipeInstructions(i);
                    if (generalRecipeSelected != null){
                        generalRecipeSelected.setAnalyzedRecipeInstructions(i);
                    }
                    // If everything goes right, you should see information on log
                    Log.d("spoonacularService.getAnalyzedRecipeInstructions", i.toString());
                }

                changeFragment(RecipeDetailsFragment.getInstance(),RecipeDetailsFragment.TAG,true );

            }

            @Override
            public void onFailure(Call<List<AnalyzedRecipeInstructions>> call, Throwable t) {
      //          Log.d("spoonacularService.getAnalyzedRecipeInstructions", t.toString());
            }
        });
    }

    public List<GeneralRecipe> getGeneralRecipes() {
        return generalRecipes;
    }

    public GeneralRecipe getGeneralRecipeSelected() {
        return generalRecipeSelected;
    }

    public void setGeneralRecipeSelected(GeneralRecipe generalRecipeSelected) {
        this.generalRecipeSelected = generalRecipeSelected;
    }

    public Globals getGlobals() {
        return g;
    }
}
