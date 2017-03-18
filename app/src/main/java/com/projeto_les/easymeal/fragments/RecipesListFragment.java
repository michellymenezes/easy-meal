package com.projeto_les.easymeal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.adapters.RecipeListViewAdapter;
import com.projeto_les.easymeal.models.GeneralRecipe;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;

import java.util.ArrayList;
import java.util.Arrays;

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
        final ArrayList<GeneralRecipe> recipeList = (ArrayList<GeneralRecipe>) ((MainActivity) getActivity()).getRecipes();

        ListView  recipesListView = (ListView ) view.findViewById(R.id.recipes_result);



        if(recipeList==null || recipeList.isEmpty()){
            view.findViewById(R.id.no_result).setVisibility(View.VISIBLE);
            view.findViewById(R.id.scrollView_recipes).setVisibility(View.GONE);
        }
        else {
            view.findViewById(R.id.no_result).setVisibility(View.GONE);
            view.findViewById(R.id.scrollView_recipes).setVisibility(View.VISIBLE);

            recipesListView.setAdapter(new RecipeListViewAdapter(getActivity(), recipeList));
            ListUtils.setDynamicHeight(recipesListView);
        }


        return view;
    }


    // Para adaptar o scroll view ao tamanho da lista de receitas
    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter listAdapter = mListView.getAdapter();
            if (listAdapter != null) {

                int numberOfItems = listAdapter.getCount();

                // Get total height of all items.
                int totalItemsHeight = 0;
                for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                    View item = listAdapter.getView(itemPos, null, mListView);

                    float px = 500 * (mListView.getResources().getDisplayMetrics().density);
                    item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST),
                            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

                    totalItemsHeight += item.getMeasuredHeight();
                }

                // Get total height of all item dividers.
                int totalDividersHeight = mListView.getDividerHeight() *
                        (numberOfItems - 1);
                // Get padding
                int totalPadding = mListView.getPaddingTop() + mListView.getPaddingBottom();

                // Set list height.
                ViewGroup.LayoutParams params = mListView.getLayoutParams();
                params.height = totalItemsHeight + totalDividersHeight + totalPadding + 50;
                mListView.setLayoutParams(params);
                mListView.requestLayout();
            }
        }
    }


}
