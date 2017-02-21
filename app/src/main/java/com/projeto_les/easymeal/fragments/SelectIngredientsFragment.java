package com.projeto_les.easymeal.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.projeto_les.easymeal.IngredientListAdapter;
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
    private Button mClearBtn;
    private EditText mIngredientEditText;
    private Button mAddBtn;

    private List<String> mRecentlyAddedIngts;
    private List<String> mIngredients;
    private ArrayAdapter mListAdapter;
    private ListView mSelectIngListView;


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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_select_ingredient, container, false);

        mRecentlyAddedIngts = new ArrayList<String>();
        mIngredients = new ArrayList<String>();
        mListAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, mIngredients);

        mListAdapter = new IngredientListAdapter(getActivity(), mIngredients);

        final ImageButton backMenuBtn = (ImageButton) view.findViewById(R.id.back_menu_btn);
        final ImageButton nextBtn = (ImageButton) view.findViewById(R.id.next);

        mSelectIngListView = (ListView) view.findViewById(R.id.selected_ingredients_list);
        mIngredientEditText = (EditText) view.findViewById(R.id.auto_complete_ingredient);
        //actv.setTextLocale(Locale.ENGLISH);
        mClearBtn = (Button)view.findViewById(R.id.clear_btn);

        mAddBtn = (Button) view.findViewById(R.id.add);
        mAddBtn.setEnabled(true);



        clear();

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
                if (mIngredients.size() > 0){
                    ((MainActivity) getActivity()).changeFragment(SelectFiltersFragment.getInstance(),SelectFiltersFragment.TAG,true );
                } else {
                    Toast.makeText(getContext(), R.string.add_one, Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void clear(){
        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIngredientEditText.setText("");
            }
        });

    }

    private void addIngredient() {
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ingredient = mIngredientEditText.getText().toString().toUpperCase().trim();

                if(!mIngredients.contains(ingredient)){
                    mIngredients.add(ingredient);
                } else{
                    Toast.makeText(getContext(), R.string.already_exist, Toast.LENGTH_SHORT).show();
                }
                mSelectIngListView.setAdapter(mListAdapter);
                hideKeyboard(getActivity());
                mIngredientEditText.setText("");
            }
        });
    }

    private void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

   /* private String ingredientsRestrictions(String ingredient){
        String ingred = "";
        ArrayList<String> words = new ArrayList<>();

        if (!mIngredients.contains(ingredient) ){
        }

        return  ingredient;
    }*/
}
