package com.example.rucafeandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import entities.Coffee;
import entities.Donut;
import entities.MenuItem;
import entities.Order;

/**
 * Adapter associated with the recyclerview in the All Orders activity.
 *
 * @author Ashley Berlinski, Francisco Marquez
 */
public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.AllOrderHolder> {

    private Context context;
    private ObservableArrayList<MenuItem> items;

    /**
     * Constructor for the adapter.
     * @param context the context of the activity
     * @param order the dataset to be used for the adapter.
     */
    public AllOrdersAdapter(Context context, Order order) {
        this.context = context;
        this.items = order.getMenuItems();
    }

    /**
     * Inflates the holders for the all orders recycler view.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the the all order adapter.
     */
    @NonNull
    @Override
    public AllOrdersAdapter.AllOrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_all_order_view, parent, false);

        return new AllOrderHolder(view, this);
    }

    /**
     * Sets values of the holders.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull AllOrdersAdapter.AllOrderHolder holder, int position) {
        holder.tv_price.setText(items.get(position).getPriceString());
        holder.tv_description.setText(items.get(position).toString());
        String name = "";
        MenuItem item = items.get(position);
        if (item instanceof Coffee){
            name = "Coffee";
        }
        else if (item instanceof Donut) {
            name = "Donut";
        }
        else {
            name = "Sandwich";
        }
        holder.tv_name.setText(name.toString());
        holder.imageView.setImageResource(items.get(position).getImage());
    }

    /**
     * Returns total items in the dataset.
     * @return total items in the dataset.
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * Clears the current data set of all of its items.
     */
    public void clear() {
        items.clear();
        Log.d("clear", "clear: ");
        notifyDataSetChanged();
    }

    /**
     * Sets the adapters dataset to a given order.
     * @param order the new order that will act as the adapter's dataset.
     */
    public void updateOrder(Order order) {
        this.items = order.getMenuItems();
    }

    /**
     * Inner holder class used to display each menu item of an order.
     */
    public static class AllOrderHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_description, tv_price;
        private ImageView imageView;

        /**
         * Constructor for the holder, binds components to their layout.
         * @param itemView the view of the holder
         * @param adapter the adapter associated with the holder
         */
        public AllOrderHolder (@NonNull View itemView, AllOrdersAdapter adapter) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.textView_ItemName);
            tv_description = itemView.findViewById(R.id.textView_ItemDescription);
            tv_price = itemView.findViewById(R.id.textView_ItemPrice);
            imageView = itemView.findViewById(R.id.iv_all_order);
        }
    }
}


