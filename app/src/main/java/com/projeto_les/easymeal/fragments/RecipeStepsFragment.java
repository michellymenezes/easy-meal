package com.projeto_les.easymeal.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projeto_les.easymeal.R;


public class RecipeStepsFragment extends Fragment {

    public static final String TAG = "STEPS_FRAGMENT";

    private RecyclerView mRecycleView;
    private View mview;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeStepsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RecipeStepsFragment getInstance() {
        RecipeStepsFragment fragment = new RecipeStepsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mview = inflater.inflate(R.layout.fragment_recipe_steps, container, false);
        startAdapter();

        return mview;
    }

    private void startAdapter() {
        //mRecycleView = (RecyclerView) mview.findViewById(R.id.recipe_ingredient_list);
       // mRecycleView.setHasFixedSize(true);

      //  LinearLayoutManager llm = new LinearLayoutManager(getActivity());
     //   llm.setOrientation(LinearLayoutManager.VERTICAL);
      //  mRecycleView.setLayoutManager(llm);


//        FoundFeedCardAdapter adapter = new FoundFeedCardAdapter(getActivity(),mList);
//        adapter.setRecycleViewOnClickListener(this);
//        mRecycleView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
