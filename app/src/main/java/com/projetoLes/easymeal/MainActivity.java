package com.projetoLes.easymeal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.projetoLes.easymeal.fragments.InitialFragment;
import com.projetoLes.easymeal.fragments.SelectIngredientsFragment;

public class MainActivity extends AppCompatActivity {

    private InitialFragment initialFragment;
    private SelectIngredientsFragment selectIngredientsFragment;

    public static final String TAG = "MAIN_ACTIVITY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        initialFragment = InitialFragment.getInstance();
        selectIngredientsFragment = SelectIngredientsFragment.getInstance();

        changeFragment(initialFragment, InitialFragment.TAG, true);



        //Quando precisar iniciar a conexão a Key deve ser utilizada da seguinte maneira: getString(R.string.SPOONACULATOR_API_KEY)

    }

    private void changeFragment(Fragment frag, String tag, boolean saveInBackstack) {


        try {
            FragmentManager manager = getSupportFragmentManager();
            //fragment not in back stack, create it.
            FragmentTransaction transaction = manager.beginTransaction();


            transaction.replace(R.id.content_layout, frag, tag);

            if (saveInBackstack) {
                Log.d(TAG, "Change Fragment: addToBackTack " + tag);
                transaction.addToBackStack(tag);
            } else {
                Log.d(TAG, "Change Fragment: NO addToBackTack");
            }
            transaction.commit();
            // custom effect if fragment is already instanciated

        } catch (IllegalStateException exception) {
            Log.w(TAG, "Unable to commit fragment, could be activity as been killed in background. " + exception.toString());
        }
    }

    @Override
    public void onBackPressed() {
        int fragments = getSupportFragmentManager().getBackStackEntryCount();
        if (fragments == 1) {
            finish();
            return;
        }

        super.onBackPressed();
    }


    public void onSelectIngredientsButtonPressed(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_layout,
                selectIngredientsFragment, SelectIngredientsFragment.TAG).addToBackStack(SelectIngredientsFragment.TAG).commit();

    }
}
