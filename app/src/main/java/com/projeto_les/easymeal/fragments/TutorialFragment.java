package com.projeto_les.easymeal.fragments;

/**
 * Created by samirsmedeiros on 28/02/17.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.adapters.RecipeIngredientsAdapter;
import com.projeto_les.easymeal.services.retrofit_models.ExtendedIngredient;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;
import com.xiaofeng.flowlayoutmanager.Alignment;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Um Fragment para mostrar uma lista com todos os objetos encontrados.
 */
public class TutorialFragment extends Fragment {


    public static final String TAG = "TUTORIAL_FRAGMENT";

    private RecyclerView mRecycleView;
    private View mview;
    private List<String> mIngredients;
    private RecipeInformation mRecipe;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TutorialFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TutorialFragment getInstance() {
        TutorialFragment fragment = new TutorialFragment();
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

        mview = inflater.inflate(R.layout.fragment_tutorial, container, false);

        return mview;
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