package com.projeto_les.easymeal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;


/**
 * Created by michelly on 18/02/17.
 */

public class InitialFragment extends Fragment{
    static InitialFragment fragment;
    public static final String TAG = "INITIAL_FRAGMENT";



    public InitialFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment.
     * @return A new instance of fragment InitialFragment.
     */
    public static InitialFragment getInstance() {
        if (fragment == null ){
            fragment = new InitialFragment();
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
        View view = inflater.inflate(R.layout.fragment_initial, container, false);

        Button selectIngredients = (Button) view.findViewById(R.id.select_ingredients_btn);
        Button favorite = (Button) view.findViewById(R.id.favorites_btn);
        Button help = (Button) view.findViewById(R.id.help_btn);

        selectIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(SelectIngredientsFragment.getInstance(),SelectIngredientsFragment.TAG,true );
            }
        });

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.not_ready), Toast.LENGTH_LONG).show();
                //((MainActivity)getActivity()).changeFragment();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), getString(R.string.not_ready), Toast.LENGTH_LONG).show();
                //((MainActivity)getActivity()).changeFragment();
            }
        });
        return view;

    }

}
