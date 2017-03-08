package com.projeto_les.easymeal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.projeto_les.easymeal.Globals;
import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.fragments.RecipeDetailsFragment;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformationMapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeListViewAdapter extends ArrayAdapter {
    public static final String TAG = "RECIPE_LIST_VIEW_ADAPTER";


    private List<String[]> items;
    private Activity activity;

    public RecipeListViewAdapter(Activity activity, List<String[]> items) {
        super(activity, android.R.layout.simple_list_item_1,items );

        this.items = items;
        this.activity = activity;
    }

    @Override
    public String[] getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount(){
        return items.size();

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final String id = items.get(position)[0];
        final String name = items.get(position)[1];
        final String image = items.get(position)[2];

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, null);
        }

        TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_item_name);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipe_image);

        recipeName.setText(name);
        //recipeImage.setImageDrawable();

        recipeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedRecipe = new Intent(view.getContext(), MainActivity.class);

                //TODO adicionar na intet o valor real de ID das receitas
                selectedRecipe.putExtra("SELECTED_RECIPE", Integer.parseInt(id));

                ((MainActivity) activity).getRecipeInformation(Integer.parseInt(id), false);

                ((MainActivity)activity).changeFragment(RecipeDetailsFragment.getInstance(),RecipeDetailsFragment.TAG,true );

                // para pegar o id em qualquer lugar
               /* Intent intent = ((MainActivity) activity).getIntent();
               int id = (Integer) intent.getParcelableExtra("SELECTED_RECIPE"); */

            }
        });



        return convertView;
    }

}
