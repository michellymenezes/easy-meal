package com.projeto_les.easymeal.fragments;

/**
 * Created by samirsmedeiros on 28/02/17.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.interfaces.RecycleViewOnClickListener;

import java.util.List;

/**
 * Um Fragment para mostrar uma lista com todos os objetos encontrados.
 */
public class RecipeIngredientsFragment extends Fragment {


    public static final String TAG = "RECIPE_INGREDIENTS_FRAGMENT";

    private RecyclerView mRecycleView;
    private View mview;


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

        return mview;
    }

    private void startAdapter() {

      //  LinearLayoutManager llm = new LinearLayoutManager(getActivity());
       // llm.setOrientation(LinearLayoutManager.VERTICAL);
      //  mRecycleView.setLayoutManager(llm);


            //   FoundFeedCardAdapter adapter = new FoundFeedCardAdapter(getActivity(),mList);
//        adapter.setRecycleViewOnClickListener(this);
//        mRecycleView.setAdapter(adapter);
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
