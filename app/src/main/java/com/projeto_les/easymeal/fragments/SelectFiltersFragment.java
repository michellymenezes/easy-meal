package com.projeto_les.easymeal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;


/**
 * Created by michelly on 18/02/17.
 */

public class SelectFiltersFragment extends Fragment {

    private static SelectFiltersFragment fragment;
    public static final String TAG = "SELECT_FILTERS_FRAGMENT";

    public SelectFiltersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SelectFiltersFragment getInstance() {
        if (fragment == null ){
            fragment = new SelectFiltersFragment();
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
        final View view = inflater.inflate(R.layout.fragment_select_filters, container, false);

        final ListView checkboxListView = (ListView) view.findViewById(R.id.filter_list);

        final ImageButton backBtn = (ImageButton) view.findViewById(R.id.back);
        final ImageButton srcBtn = (ImageButton) view.findViewById(R.id.search_recipes);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(SelectIngredientsFragment.getInstance(),SelectIngredientsFragment.TAG,true );
            }
        });

        srcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), R.string.not_ready, Toast.LENGTH_LONG).show();
                //((MainActivity) getActivity()).changeFragment();
            }
        });


        return view;



    }
}
