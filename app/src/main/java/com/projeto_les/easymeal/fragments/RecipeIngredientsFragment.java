package com.projeto_les.easymeal.fragments;

/**
 * Created by samirsmedeiros on 28/02/17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.projeto_les.easymeal.Globals;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.services.retrofit_models.ExtendedIngredient;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Um Fragment para mostrar uma lista com todos os objetos encontrados.
 */
public class RecipeIngredientsFragment extends Fragment {


    public static final String TAG = "RECIPE_INGREDIENTS_FRAGMENT";

    private ListView mListView;
    private View mview;
    private List<String> mIngredients;
    private RecipeInformation mRecipe;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeIngredientsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecipeIngredientsFragment getInstance() {
        RecipeIngredientsFragment fragment = new RecipeIngredientsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_recipe_ingredients, container, false);

        startAdapter();

        Globals g = Globals.getInstance();
        mRecipe = g.getRecipeInformation();

        mListView = (ListView) mview.findViewById(R.id.recipe_ingredient_list);

        mIngredients = new ArrayList<String>();
        for (ExtendedIngredient x: mRecipe.getExtendedIngredients()) {
            mIngredients.add(x.getOriginalString());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),
                android.R.layout.simple_list_item_1, mIngredients);
        mListView.setAdapter(adapter);

        return mview;
    }

    private void startAdapter() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
