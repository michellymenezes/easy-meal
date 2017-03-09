package com.projeto_les.easymeal.adapters;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto_les.easymeal.Globals;
import com.projeto_les.easymeal.MainActivity;
import com.projeto_les.easymeal.R;
import com.projeto_les.easymeal.fragments.DownloadImageTask;
import com.projeto_les.easymeal.fragments.RecipeDetailsFragment;
import com.projeto_les.easymeal.fragments.RecipesListFragment;
import com.projeto_les.easymeal.services.retrofit_models.Recipe;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformation;
import com.projeto_les.easymeal.services.retrofit_models.RecipeInformationMapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeListViewAdapter extends ArrayAdapter {
    public static final String TAG = "RECIPE_LIST_VIEW_ADAPTER";


    private List<Recipe> items;
    private Activity activity;

    public RecipeListViewAdapter(Activity activity, List<Recipe> items) {
        super(activity, android.R.layout.simple_list_item_1,items );

        this.items = items;
        this.activity = activity;
    }

    @Override
    public Recipe getItem(int position) {
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
        final int id = items.get(position).getId();
        final String name = items.get(position).getTitle();
        final String image = items.get(position).getImage();

        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recipe_list_item, null);
        }

        TextView recipeName = (TextView) convertView.findViewById(R.id.recipe_item_name);
        ImageView recipeImage = (ImageView) convertView.findViewById(R.id.recipe_image);

        LinearLayout ll = (LinearLayout) convertView.findViewById(R.id.recipe_ll);

        new DownloadImageTask(recipeImage)
                .execute(items.get(position).getImage());

        recipeName.setText(name);



        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent selectedRecipe = new Intent(view.getContext(), MainActivity.class);

                //TODO adicionar na intet o valor real de ID das receitas
                //selectedRecipe.putExtra("SELECTED_RECIPE", Integer.parseInt(id));
                ((MainActivity) activity).setmSelectedRecipeID(id);
                ((MainActivity) activity).getRecipeInformation(id, false);
                ((MainActivity) activity).getInstructionsByStep(id, false);

                Toast.makeText(getContext(), "Wait .....  :)", Toast.LENGTH_LONG).show();


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        // Actions to do after 10 seconds
                        ((MainActivity)activity).changeFragment(RecipeDetailsFragment.getInstance(),RecipeDetailsFragment.TAG,true );
                    }
                }, 5000);


                // para pegar o id em qualquer lugar
               /* Intent intent = ((MainActivity) activity).getIntent();
               int id = (Integer) intent.getIntExtra("SELECTED_RECIPE", -1); */

            }
        });



        return convertView;
    }

}
