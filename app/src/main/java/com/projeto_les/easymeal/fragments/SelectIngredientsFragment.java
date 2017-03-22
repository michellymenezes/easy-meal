package com.projeto_les.easymeal.fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
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
    // private Button mClearBtn;
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

    private List<String> selectedFilterTypeList;
    private List<String> selectedFilterCuisineList;
    private List<String> selectedFilterDietList;


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

        mIngredients = new ArrayList<String>();

        mListAdapter = new IngredientListAdapter(mIngredients);

        final Button searchBtn = (Button) view.findViewById(R.id.search_recipes);
        searchBtn.setClickable(true);

        mSelectIngListView = (RecyclerView) view.findViewById(R.id.selected_ingredients_list);
        mSelectIngListView.setLayoutManager(new FlowLayoutManager());
        mIngredientEditText = (EditText) view.findViewById(R.id.auto_complete_ingredient);
        final Button clearBtn = (Button) view.findViewById(R.id.clear_all_ings_btn);

        //actv.setTextLocale(Locale.ENGLISH);
        // mClearBtn = (Button)view.findViewById(R.id.clear_btn);

        mAddBtn = (Button) view.findViewById(R.id.add);
        mAddBtn.setEnabled(true);

        mTypeBtn = (Button) view.findViewById(R.id.id_type);
        mCuisineBtn = (Button) view.findViewById(R.id.id_cuisine);
        mDietBtn = (Button) view.findViewById(R.id.id_diet);

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

        filterTypeList = addItens(filterTypeListName, filterTypeListIcon);
        filterCuisineList = addItens(filterCuisineListName, filterCuisineListIcon);
        filterDietList = addItens(filterDietListName, filterDietListIcon);

        selectedFilterTypeList = new ArrayList<>();
        selectedFilterCuisineList = new ArrayList<>();
        selectedFilterDietList = new ArrayList<>();


        //clear();



        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBtn.setClickable(false);
                if (mIngredients.size() > 0){
                    ((MainActivity) getActivity()).setSelectedIngredients(mIngredients);
                    ((MainActivity) getActivity()).inicializeSpoonacularService();
                   // Toast.makeText(getContext(), R.string.wait, Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(getContext(), R.string.add_one, Toast.LENGTH_SHORT).show();
                }
            }
        });

        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIngredients.clear();
                mSelectIngListView.setAdapter(mListAdapter);
            }
        });


        filterType();
        filterCuisine();
        filterDiet();


        addIngredient();


        return view;

    }

    private void filterDiet() {
        mDietBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View mView = View.inflate(getActivity(), R.layout.fragment_select_filters, null);
                final FilterListAdapter mAdapter= new FilterListAdapter(getActivity(), filterDietList, selectedFilterDietList);
                final GridView checkboxListView = (GridView) mView.findViewById(R.id.filter_list);
                final Button checkall = (Button) mView.findViewById(R.id.select_all_filters);
                if(mAdapter.allIschecked()) checkall.setText("uncheck all");
                mAdapter.setBtnCheckall(checkall);
                checkboxListView.setAdapter(mAdapter);
                checkall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAdapter.allIschecked()){
                            mAdapter.uncheckAll();
                            checkall.setText("check all");
                        }else {
                            mAdapter.checkAll();
                            checkall.setText("uncheck all");

                        }
                    }
                });
                new AlertDialog.Builder(getContext())
                        .setTitle("Select diets")
                        .setView(mView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                selectedFilterDietList = mAdapter.getSelectedItems();
                                ((MainActivity) getActivity()).setSelectedDiets(selectedFilterDietList);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(R.drawable.ic_diet_grey)
                        .show();
            }
        });
    }


    private void filterCuisine() {
        mCuisineBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View mView = View.inflate(getActivity(), R.layout.fragment_select_filters, null);
                final FilterListAdapter mAdapter= new FilterListAdapter(getActivity(), filterCuisineList, selectedFilterCuisineList);
                final GridView checkboxListView = (GridView) mView.findViewById(R.id.filter_list);
                final Button checkall = (Button) mView.findViewById(R.id.select_all_filters);
                mAdapter.setBtnCheckall(checkall);
                if(mAdapter.allIschecked()) checkall.setText("uncheck all");
                checkboxListView.setAdapter(mAdapter);
                checkall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAdapter.allIschecked()){
                            mAdapter.uncheckAll();
                            checkall.setText("check all");
                        }else {
                            mAdapter.checkAll();
                            checkall.setText("uncheck all");

                        }
                    }
                });
                new AlertDialog.Builder(getContext())
                        .setTitle("Select cuisines")
                        .setView(mView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                selectedFilterCuisineList = mAdapter.getSelectedItems();
                                ((MainActivity) getActivity()).setSelectedCuisines(selectedFilterCuisineList);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(R.drawable.ic_cuisine_grey)
                        .show();
            }
        });
    }

    private void filterType() {
        mTypeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                View mView = View.inflate(getActivity(), R.layout.fragment_select_filters, null);
                final FilterListAdapter mAdapter= new FilterListAdapter(getActivity(), filterTypeList, selectedFilterTypeList);
                final GridView checkboxListView = (GridView) mView.findViewById(R.id.filter_list);
                final Button checkall = (Button) mView.findViewById(R.id.select_all_filters);
                mAdapter.setBtnCheckall(checkall);
                if(mAdapter.allIschecked()) checkall.setText("uncheck all");
                checkboxListView.setAdapter(mAdapter);
                checkall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mAdapter.allIschecked()){
                            mAdapter.uncheckAll();
                            checkall.setText("check all");
                        }else {
                            mAdapter.checkAll();
                            checkall.setText("uncheck all");

                        }
                    }
                });
                new AlertDialog.Builder(getContext())
                        .setTitle("Select recipe types")
                        .setView(mView)
                        .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                selectedFilterTypeList = mAdapter.getSelectedItems();
                                ((MainActivity) getActivity()).setSelectedFilters(selectedFilterTypeList);


                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setIcon(R.drawable.ic_cook)
                        .show();
            }
        });
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
}
