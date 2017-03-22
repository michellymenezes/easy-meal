package com.projeto_les.easymeal.adapters;

    import android.support.v7.widget.RecyclerView;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.Button;
    import android.widget.ImageButton;

    import com.projeto_les.easymeal.R;
    import com.projeto_les.easymeal.models.ChipView;

    import java.util.List;


public class IngredientListAdapter extends RecyclerView.Adapter {

        private List<String> items;
        private Button clear;

        public IngredientListAdapter(List<String> items, Button clear) {
            this.items = items;
            this.clear = clear;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ChipViewHolder(new ChipView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            View mView =  ((ChipView)holder.itemView);
            ((ChipView)holder.itemView).displayItem(items.get(position));
            ImageButton removeIngredientButton = (ImageButton) mView.findViewById(R.id.view_chip_close_button);

            removeIngredientButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeIngredient(position);
                }
            });
            hideClearBtn();



        }

    private void hideClearBtn() {
        if(items.size()>0) clear.setVisibility(View.VISIBLE);
        else clear.setVisibility(View.GONE);
    }

    private void removeIngredient(int position){
        items.remove(position);
        notifyDataSetChanged();
    }

        @Override
        public int getItemCount() {
            return items.size();
        }

        private class ChipViewHolder extends RecyclerView.ViewHolder {

            public ChipViewHolder(View itemView) {
                super(itemView);
            }
        }
    }


