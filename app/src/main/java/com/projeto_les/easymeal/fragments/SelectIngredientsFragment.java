package com.projeto_les.easymeal.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by michelly on 18/02/17.
 */

public class SelectIngredientsFragment extends Fragment {

    private static SelectIngredientsFragment fragment;
    public static final String TAG = "SELECT_INGREDIENTS_FRAGMENT";

    private List<String> recentlyAddedIngts;
    private List<String> ingredients;

    public SelectIngredientsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SearchFragment.
     */
    public static SelectIngredientsFragment getInstance() {
        if (fragment == null ){
            fragment = new SelectIngredientsFragment();
            Bundle args = new Bundle();
            fragment.setArguments(args);
        }

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recentlyAddedIngts = new ArrayList<String>();
        ingredients = new ArrayList<String>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_select_ingredient, container, false);
        final Button addBtn = (Button) view.findViewById(R.id.add);
        addBtn.setEnabled(true);
        final ListView selectIngListView = (ListView) view.findViewById(R.id.selected_ingredients_list);
        final ImageButton backMenuBtn = (ImageButton) view.findViewById(R.id.back_menu_btn);
        final ImageButton nextBtn = (ImageButton) view.findViewById(R.id.next);
        final AutoCompleteTextView actv= (AutoCompleteTextView)view.findViewById(R.id.auto_complete_ingredient);

        final ArrayAdapter mListAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, ingredients);

        Button clearBtn = (Button)view.findViewById(R.id.clear_btn);

        backMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(InitialFragment.getInstance(),InitialFragment.TAG,true );
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(SelectFiltersFragment.getInstance(),SelectFiltersFragment.TAG,true );

            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ingredients.contains(actv.getText().toString())) {
                    ingredients.add(actv.getText().toString());
                }
                selectIngListView.setAdapter(mListAdapter);

            }
        });
        return view;
    }
}
