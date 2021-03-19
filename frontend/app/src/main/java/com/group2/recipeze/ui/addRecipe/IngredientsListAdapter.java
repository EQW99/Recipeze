package com.group2.recipeze.ui.addRecipe;


import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.group2.recipeze.R;

import java.util.ArrayList;

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.ViewHolder> {

    private ArrayList<String> ingredients;
    private ArrayList<String> ingredientsAmounts;

    public static class IngredientDialog extends AlertDialog {
        AlertDialog dialogBuilder;

        public IngredientDialog(View view, IngredientsListAdapter ingredientsListAdapter) {
            super(view.getContext());
            dialogBuilder = new AlertDialog.Builder(view.getContext()).create();
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_ingredient, null);

            EditText amount = dialogView.findViewById(R.id.amountEdit);
            EditText ingredient = dialogView.findViewById(R.id.ingredientEdit);
            Button button1 = dialogView.findViewById(R.id.buttonSubmit);
            Button button2 = dialogView.findViewById(R.id.buttonCancel);


            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogBuilder.dismiss();
                }
            });
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ingredientsListAdapter.addItem(amount.getText().toString(), ingredient.getText().toString());
                    dialogBuilder.dismiss();
                }
            });

            dialogBuilder.setView(dialogView);
        }

        public void show() {
            dialogBuilder.show();
        }
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView cross;
        private final TextView ingredient;
        private int position;
        private IngredientsListAdapter parent;

        public ViewHolder(View view, IngredientsListAdapter parent) {
            super(view);
            this.parent = parent;
            ingredient = view.findViewById(R.id.ingredient);
            cross = view.findViewById(R.id.cross);

            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parent.removeItem(position);
                }
            });
        }

        public TextView getIngredient() {return this.ingredient;}

        public void setPosition(int position) {
            this.position = position;
        }

    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param ingredients String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public IngredientsListAdapter(ArrayList<String> ingredients, ArrayList<String> ingredientsAmounts) {
        this.ingredients = ingredients;
        this.ingredientsAmounts = ingredientsAmounts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_ingredient, viewGroup, false);


        return new ViewHolder(view, this);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.setIsRecyclable(false);
        viewHolder.setPosition(position);
        viewHolder.getIngredient().setText(this.ingredientsAmounts.get(position) + " " + this.ingredients.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public void newIngredient(View view) {
        IngredientDialog dialog = new IngredientDialog(view, this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void addItem(String amount, String ingredient) {
        ingredients.add(ingredient);
        ingredientsAmounts.add(amount);
        notifyDataSetChanged();
    }

    public void removeItem(int position) {
        ingredients.remove(position);
        ingredientsAmounts.remove(position);
        notifyDataSetChanged();
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public ArrayList<String> getIngredientsAmounts() {
        return ingredientsAmounts;
    }

}

