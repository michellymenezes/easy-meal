package com.projeto_les.easymeal.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto_les.easymeal.adapters.FilterListAdapter;
import com.projeto_les.easymeal.adapters.IngredientListAdapter;
import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.models.FilterItem;
import com.xiaofeng.flowlayoutmanager.FlowLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by michelly on 18/02/17.
 */

public class SelectIngredientsFragment extends Fragment {

    private static SelectIngredientsFragment fragment;
    public static final String TAG = "SELECT_INGREDIENTS_FRAGMENT";
    private EditText mIngredientEditText;
    private Button mAddBtn;
    private Button mTypeBtn;
    private Button mCuisineBtn;
    private Button mDietBtn;

    private List<String> mIngredients;
    private IngredientListAdapter mListAdapter;
    private RecyclerView mSelectIngListView;

    private List<FilterItem> filterTypeList;
    private List<FilterItem> filterCuisineList;
    private List<FilterItem> filterDietList;

    private List<String> filterTypeListName;
    private List<String> filterCuisineListName;
    private List<String> filterDietListName;

    private int[] filterTypeListIcon;
    private int[] filterCuisineListIcon;
    private int[] filterDietListIcon;

    private List<String> selectedFilterList;
    private List<String> selectedFilterCuisineList;
    private List<String> selectedFilterDietList;
    private Button mClearBtn;

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

        final FloatingActionButton searchBtn = (FloatingActionButton) view.findViewById(R.id.search_recipes);
        searchBtn.setClickable(true);

        initVariables(view);
        setClickListenerToSearchBtn(searchBtn);
        setClickListenerToClearBtn();

        addIngredient();

        return view;

    }

    private void setClickListenerToClearBtn() {
        mClearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIngredients.clear();
                mSelectIngListView.setAdapter(mListAdapter);
            }
        });
    }

    private void setClickListenerToSearchBtn(final FloatingActionButton searchBtn) {
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBtn.setClickable(false);
                if (mIngredients.size() > 0){
                    ((MainActivity) getActivity()).setSelectedIngredients(mIngredients);
                    ((MainActivity) getActivity()).inicializeSpoonacularService();

                } else {
                    Toast.makeText(getContext(), R.string.add_one, Toast.LENGTH_SHORT).show();
                    searchBtn.setClickable(true);
                }
            }
        });
    }

    private void initVariables(View view) {
        mIngredients = new ArrayList<>();

        initClearBtn(view);

        mListAdapter = new IngredientListAdapter(mIngredients, mClearBtn);

        mSelectIngListView = (RecyclerView) view.findViewById(R.id.selected_ingredients_list);
        mSelectIngListView.setLayoutManager(new FlowLayoutManager());
        mIngredientEditText = (EditText) view.findViewById(R.id.auto_complete_ingredient);

        initSelectedFilter();

        initAddBtn(view);
        initFilters();
        addItensToFilters();
        initFiltersBtn(view);
    }

    private void initFiltersBtn(View view) {

        mTypeBtn = (Button) view.findViewById(R.id.id_type);
        filterBuilder(mTypeBtn, R.string.select_recipe_type, new FilterListAdapter(getActivity(), filterTypeList, selectedFilterList));

        mCuisineBtn = (Button) view.findViewById(R.id.id_cuisine);
        filterBuilder(mCuisineBtn, R.string.select_cuisines, new FilterListAdapter(getActivity(), filterCuisineList, selectedFilterCuisineList));

        mDietBtn = (Button) view.findViewById(R.id.id_diet);
        filterBuilder(mDietBtn, R.string.select_diets, new FilterListAdapter(getActivity(), filterDietList, selectedFilterDietList));

    }

    private void initSelectedFilter() {
        selectedFilterList = new ArrayList<>();
        selectedFilterCuisineList = new ArrayList<>();
        selectedFilterDietList = new ArrayList<>();
    }

    private void addItensToFilters() {
        filterTypeList = addItens(filterTypeListName, filterTypeListIcon);
        filterCuisineList = addItens(filterCuisineListName, filterCuisineListIcon);
        filterDietList = addItens(filterDietListName, filterDietListIcon);
    }

    private void initFilters() {
        filterCuisineListName = new ArrayList<>(Arrays.asList( "African", "Chinese", "Japanese",
                "Korean", "Vietnamese", "Thai", "Indian", "British", "French", "Italian", "Mexican"
                , "American", "Greek", "Latin American"));

        filterTypeListName = new ArrayList<>(Arrays.asList( "Main Course", "Side Dish", "Dessert",
                "Appetizer", "Salad", "Breakfast", "Soup", "Beverage", "Sauce", "Drink"));

        filterDietListName = new ArrayList<>(Arrays.asList( "Pescetarian", "Lacto Vegetarian",
                "Ovo Vegetarian", "Vegan", "Paleo", "Primal", "Vegetarian"));

        filterTypeListIcon = new int []{R.drawable.ic_main_course, R.drawable.ic_side_dish,
                R.drawable.ic_dessert, R.drawable.ic_appetizer,
                R.drawable.ic_salad, R.drawable.ic_breakfast,
                R.drawable.ic_soup, R.drawable.ic_beverage, R.drawable.ic_sauce,
                R.drawable.ic_drink};

        filterCuisineListIcon = new int []{R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
                R.drawable.ic_cuisine, R.drawable.ic_cuisine, R.drawable.ic_cuisine,
        };

        filterDietListIcon = new int []{R.drawable.ic_diet, R.drawable.ic_diet,
                R.drawable.ic_diet, R.drawable.ic_diet, R.drawable.ic_diet,
                R.drawable.ic_diet, R.drawable.ic_diet,
        };
    }

    private void initAddBtn(View view) {
        mAddBtn = (Button) view.findViewById(R.id.add);
        mAddBtn.setEnabled(true);
    }

    private void initClearBtn(View view) {
        mClearBtn = (Button) view.findViewById(R.id.clear_all_ings_btn);
        mClearBtn.setVisibility(View.GONE);
    }



    private void filterBuilder(Button btn, final int filter, final FilterListAdapter adapter) {
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View mView = View.inflate(getActivity(), R.layout.fragment_select_filters, null);
                final GridView checkboxListView = (GridView) mView.findViewById(R.id.filter_list);
                final Button checkall = (Button) mView.findViewById(R.id.select_all_filters);
                if(adapter.allIschecked()) checkall.setText("uncheck all");
                adapter.setBtnCheckall(checkall);
                checkboxListView.setAdapter(adapter);
                checkall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (adapter.allIschecked()){
                            adapter.uncheckAll();
                            checkall.setText("check all");
                        }else {
                            adapter.checkAll();
                            checkall.setText("uncheck all");

                        }
                    }
                });

                alertDialogBuilder(mView, adapter, filter);
            }
        });
    }

    private void alertDialogBuilder(View view, final FilterListAdapter adapter, final int filter){

        new AlertDialog.Builder(getContext())
                .setTitle(filter)
                .setView(view)
                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if (filter== R.string.select_recipe_type){
                            selectedFilterList = adapter.getSelectedItems();
                            ((MainActivity) getActivity()).setSelectedFilters(selectedFilterList);
                        }else if (filter== R.string.select_cuisines){
                            selectedFilterCuisineList = adapter.getSelectedItems();
                            ((MainActivity) getActivity()).setSelectedCuisines(selectedFilterCuisineList);
                        }else if (filter== R.string.select_diets){
                            selectedFilterDietList = adapter.getSelectedItems();
                            ((MainActivity) getActivity()).setSelectedDiets(selectedFilterDietList);
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(R.drawable.ic_cook)
                .show();
    }


    private void addIngredient() {
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addIngredientAux();
            }
        });
        mIngredientEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_NEXT){
                    addIngredientAux();
                }
                return handled;
            }
        });

    }

    private void addIngredientAux(){
        String ingredient = mIngredientEditText.getText().toString().toUpperCase().trim();
        if(ingredientsRestrictions(ingredient)){
            if (!mIngredients.contains(ingredient)) {
                mIngredients.add(ingredient);
            } else {
                Toast.makeText(getContext(), R.string.already_exist, Toast.LENGTH_SHORT).show();
            }
        }
        mSelectIngListView.setAdapter(mListAdapter);
        hideKeyboard(getActivity());
        mIngredientEditText.setText("");

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

    private boolean ingredientsRestrictions(String ingredient){
        ingredient = ingredient.trim();

        if (ingredient==null || ingredient.equals("")){
            Toast.makeText(getContext(), R.string.empty_ingredient, Toast.LENGTH_SHORT).show();
            return false;
        } if (ingredient.matches(".*\\d.*")){
            Toast.makeText(getContext(), R.string.contain_number, Toast.LENGTH_SHORT).show();
            return false;
        }/* if (ingredient.matches(".*\\W.*") ){
            Toast.makeText(getContext(), R.string.contain_metacharacter, Toast.LENGTH_SHORT).show();
            return false;
        }*/
        return  true;
    }

    private List<FilterItem> addItens(List<String> filterListName, int[] filterListIcon) {
        ArrayList<FilterItem> filters = new ArrayList<>();
        for (int i = 0; i < filterListName.size(); i++) {
            filters.add(new FilterItem(filterListName.get(i),filterListIcon[i]));
        }
        return filters;
    }

    //TODO ajustar o update da view
    public void updateView() {

        mListAdapter = new IngredientListAdapter(((MainActivity) getActivity()).getSelectedIngredients(), mClearBtn);
        mSelectIngListView.setAdapter(mListAdapter);

        selectedFilterList = ((MainActivity) getActivity()).getSelectedFilters();
        selectedFilterCuisineList = ((MainActivity) getActivity()).getSelectedCuisines();
        selectedFilterDietList = ((MainActivity) getActivity()).getSelectedDiets();

    }
}
