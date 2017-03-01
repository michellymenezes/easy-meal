package com.projeto_les.easymeal.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.adapters.RecipeSwipeAdapter;

/**
 * Created by samirsmedeiros on 28/02/17.
 */

public class RecipeDetailsFragment extends Fragment {

    public static final String TAG = "DETAILS_FRAGMENT";

    private RecipeSwipeAdapter mAdapter;
    private ViewPager mPager;

    public RecipeDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.2.
     * @return A new instance of fragment FeedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecipeDetailsFragment getInstance() {
        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
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

        View feed_view = inflater.inflate(R.layout.fragment_recipe_details, container, false);

        mAdapter = new RecipeSwipeAdapter(getChildFragmentManager());
        mPager = (ViewPager) feed_view.findViewById(R.id.feed_pager);
        mPager.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return feed_view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
