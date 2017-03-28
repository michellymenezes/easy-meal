package com.projeto_les.easymeal.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.adapters.RecipeListViewAdapter;
import com.projeto_les.easymeal.models.GeneralRecipe;

import java.util.ArrayList;
import java.util.Collections;

public class RecipesListFragment extends Fragment {

        private static RecipesListFragment fragment;
        public static final String TAG = "LIST_RECIPES_FRAGMENT";

    public RecipesListFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment SearchFragment.
         */
    public static RecipesListFragment getInstance() {
        if (fragment == null) {
            fragment = new RecipesListFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_recipes_list, container, false);

        //TODO pegar receitas compatíveis com a pesquisa
        final ArrayList<GeneralRecipe> recipeList = (ArrayList<GeneralRecipe>) ((MainActivity) getActivity()).getGeneralRecipes();

        Collections.sort(recipeList);

        RecyclerView recipesListView = (RecyclerView) view.findViewById(R.id.recipes_result);
        recipesListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        initHandler(view);
        setRecipeListViewVisibility(view, recipeList, recipesListView);

        return view;
    }

    private void initHandler(final View view) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                view.findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            }
        }, 4000);
    }

    private void setRecipeListViewVisibility(View view, ArrayList<GeneralRecipe> recipeList, RecyclerView recipesListView) {
        if(recipeList==null || recipeList.isEmpty()){
            view.findViewById(R.id.no_result).setVisibility(View.VISIBLE);
            view.findViewById(R.id.recipes_list).setVisibility(View.GONE);
        }
        else {
            view.findViewById(R.id.no_result).setVisibility(View.GONE);
            view.findViewById(R.id.recipes_list).setVisibility(View.VISIBLE);

            recipesListView.setAdapter(new RecipeListViewAdapter(getActivity(), recipeList));
        }
    }


}
