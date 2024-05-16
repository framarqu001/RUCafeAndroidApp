package com.example.rucafeandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import entities.Donut;
import entities.Order;

/**
 * Adapter associated with the recyclerview in the Donut activity.
 *
 * @author Lily Chang, Ashley Berlinski
 */
class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder>{
    private Context context; //need the context to inflate the layout
    private ArrayList<Donut> donuts;

    /**
     * Constructor for the adapter.
     * @param context the context of the activity
     * @param donuts the dataset to be used for the adapter.
     */
    public DonutAdapter(Context context, ArrayList<Donut> donuts) {
        this.context = context;
        this.donuts = donuts;

    }

    /**
     * Adds the corresponding donut to the user's order and resets the quantity of the holder
     * of that specific donut item.
     * @param holder the holder containing the donut to be added.
     */
    private void addDonutToOrder(@NonNull DonutHolder holder) {
        int pos = holder.getAdapterPosition();
        Order order = Order.getInstance();
        order.addToOrder(new Donut(donuts.get(pos)));
        holder.spinner_quantity.setSelection(0);
        donuts.get(pos).setQuantity((Integer)holder.spinner_quantity.getSelectedItem());
        holder.tv_price.setText(donuts.get(pos).getPriceString());
    }

    /**
     * Asks if user wishes to add the donut to their order and if yes does so.
     * @param holder the holder containing the donut that may be added.
     */
    private void addToOrderPrompt(@NonNull DonutHolder holder) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Add to order");
        alert.setMessage(donuts.get(holder.getAdapterPosition()).toString());
        //handle the "YES" click
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                addDonutToOrder(holder);
                Toast.makeText(context,
                        holder.tv_name.getText().toString() + " added.", Toast.LENGTH_LONG).show();
            }

            //handle the "NO" click
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //
                }
            });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     * Inflates the holders for the donut recycler view.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the the all order adapter.
     */
    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflate the row layout for the items
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_view, parent, false);

        return new DonutHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        holder.tv_name.setText(donuts.get(position).getDonutText());
        holder.tv_price.setText(donuts.get(position).getPriceString());
        holder.im_item.setImageResource(donuts.get(position).getImage());
        holder.spinner_quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = holder.getAdapterPosition();
                donuts.get(pos).setQuantity((Integer) holder.spinner_quantity.getSelectedItem());
                holder.tv_price.setText(donuts.get(pos).getPriceString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { //empty
            }
        });
        holder.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToOrderPrompt(holder);
            }
        });
    }

    /**
     * Get the number of items in the ArrayList.
     * @return the number of items in the list.
     */
    @Override
    public int getItemCount() {
        return donuts.size();
    }

    /**
     * Inner holder class used to display each donut on the menu.
     */
    public static class DonutHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_price;
        private ImageView im_item;
        private Spinner spinner_quantity;
        private Button btn_add;
        private ConstraintLayout parentLayout; //this is the row layout

        /**
         * Constructor for the holder, binds components to their layout.
         * @param donutView the view of the holder
         */
        public DonutHolder(@NonNull View donutView) {
            super(donutView);
            tv_name = donutView.findViewById(R.id.tv_flavor);
            tv_price = donutView.findViewById(R.id.tv_price);
            spinner_quantity = donutView.findViewById(R.id.spinner_quantity);
            im_item = donutView.findViewById(R.id.im_item);
            btn_add = donutView.findViewById(R.id.btn_add);
            parentLayout = donutView.findViewById(R.id.rowLayout);
            setUpSpinner(donutView);
        }

        /**
         * Helper method used to set up spinners for quantity of donuts.
         * @param donutView the view of the holder
         */
        private void setUpSpinner(@NonNull View donutView) {
            int maxQuantity = 99, begin_And_Decrement = 1;
            ArrayList<Integer> quantityList = new ArrayList<>();
            for (int i = begin_And_Decrement; i <= maxQuantity; i++){
                quantityList.add(i);
            }
            ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<>(parentLayout.getContext(),
                    androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantityList);
            spinner_quantity.setAdapter(spinnerAdapter);
        }

    }
}
