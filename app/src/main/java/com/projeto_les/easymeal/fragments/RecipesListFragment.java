package com.projeto_les.easymeal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.adapters.RecipeListViewAdapter;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;

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
        final ArrayList<Recipe> recipeList = (ArrayList<Recipe>) ((MainActivity) getActivity()).getRecipes();

        Collections.sort(recipeList);

        RecyclerView recipesListView = (RecyclerView) view.findViewById(R.id.recipes_result);
        recipesListView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));



        if(recipeList==null || recipeList.isEmpty()){
            view.findViewById(R.id.no_result).setVisibility(View.VISIBLE);
            view.findViewById(R.id.recipes_list).setVisibility(View.GONE);
        }
        else {
            view.findViewById(R.id.no_result).setVisibility(View.GONE);
            view.findViewById(R.id.recipes_list).setVisibility(View.VISIBLE);

            recipesListView.setAdapter(new RecipeListViewAdapter(getActivity(), recipeList));

        }


        return view;
    }


}
