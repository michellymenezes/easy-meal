package com.projeto_les.easymeal.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by michelly on 18/02/17.
 */

public class SelectIngredientsFragment extends Fragment {

    private static SelectIngredientsFragment fragment;
    public static final String TAG = "SELECT_INGREDIENTS_FRAGMENT";
    List<String> ingredientsArrayList;
    private Button clearBtn;
    private EditText ingredientEditText;
    private Button addBtn;


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
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_select_ingredient, container, false);

        ingredientsArrayList = new ArrayList<>();

        ListView selectIngListView = (ListView) view.findViewById(R.id.selected_ingredients_list);


        addBtn = (Button) view.findViewById(R.id.add);
        addBtn.setEnabled(false);

        final ImageButton backMenuBtn = (ImageButton) view.findViewById(R.id.back_menu_btn);
        final ImageButton nextBtn = (ImageButton) view.findViewById(R.id.next);
        ingredientEditText= (EditText) view.findViewById(R.id.auto_complete_ingredient);
        //actv.setTextLocale(Locale.ENGLISH);


        clear(view);

        addIngredient();





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

        return view;
    }

    private void clear(final View view){
        if (clearBtn==null){
            clearBtn = (Button)view.findViewById(R.id.clear_btn);
        }
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ingredientEditText== null){
                    ingredientEditText= (EditText) view.findViewById(R.id.auto_complete_ingredient);
                }
                ingredientEditText.setText("");
            }
        });

    }

    private void addIngredient() {
        //(Arrays.asList("sugar", "flour", "apples"));
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                addBtn.setEnabled(false);
            }
        });
    }
}
