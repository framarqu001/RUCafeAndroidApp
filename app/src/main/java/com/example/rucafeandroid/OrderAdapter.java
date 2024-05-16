package com.example.rucafeandroid;

import android.content.Context;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import entities.Coffee;
import entities.Donut;
import entities.MenuItem;
import entities.Order;
import entities.Sandwich;

/**
 * Activity which shows the current order that the user has placed, allows
 * user to view/remove menu items and place their order.
 *
 * @author Francisco Marquez
 */
public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderHolder> {
    private Context context;
    private Order order;
    private ObservableArrayList<MenuItem> items;

    /**
     * Constructor for the adapter.
     * @param context the context of the activity
     * @param order the dataset to be used for the adapter.
     */
    public OrderAdapter(Context context, Order order) {
        this.context = context;
        this.items = order.getMenuItems();
        this.order = order;
    }

    /**
     * Inflates the holders for the order recycler view.
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return the the all order adapter.
     */
    @NonNull
    @Override
    public OrderHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_orderview, parent, false);

        return new OrderHolder(view, this);
    }

    /**
     * Sets values of the holders.
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder (@NonNull OrderHolder holder, int position) {
        holder.tv_price.setText(items.get(position).getPriceString());
        holder.tv_description.setText(items.get(position).toString());
        String name = "";
        if (items.get(position) instanceof Coffee){
            name = "Coffee";
        } else if (items.get(position) instanceof Donut){
            name = "Donut";
        } else if (items.get(position) instanceof Sandwich){
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
    public int getItemCount () {
        return items.size();
    }

    /**
     * Removes a menu item from the current order
     * @param position the position of the menu item to be removed.
     */
    public void removeItem(int position) {
        if (position < items.size()) {
            MenuItem item = order.getMenuItems().get(position);
            order.removeFromOrder(item);
            notifyDataSetChanged();
        }
    }

    /**
     * Inner holder class used to display each menu item of the order.
     */
    public static class OrderHolder extends RecyclerView.ViewHolder {
        private TextView tv_name, tv_description, tv_price;
        private Button b_remove, b_placeOrder;
        private ConstraintLayout parentLayout;
        private ImageView imageView;

        /**
         * Constructor for the holder, binds components to their layout.
         * @param itemView the view of the holder
         * @param adapter the adapter associated with the holder
         */
        public OrderHolder (@NonNull View itemView, OrderAdapter adapter) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_ItemName);
            tv_description = itemView.findViewById(R.id.tv_itemDetails);
            tv_price = itemView.findViewById(R.id.tv_itemPrice);
            b_remove = itemView.findViewById(R.id.b_itemRemove);
            b_placeOrder = itemView.findViewById(R.id.b_placeOrder);
            parentLayout = itemView.findViewById(R.id.rowOrderLayout);
            imageView = itemView.findViewById(R.id.iv_items);
            b_remove.setOnClickListener(v -> {
                int position = getAdapterPosition();
                int itemOutOfBounds = -1;
                if (position != itemOutOfBounds){
                    adapter.removeItem(position);
                }
            });
        }
    }

}
