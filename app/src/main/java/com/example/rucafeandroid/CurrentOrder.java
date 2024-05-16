package com.example.rucafeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rucafeandroid.databinding.ActivityCurrentOrderBinding;

import entities.MenuItem;
import entities.Order;
import entities.OrderList;

/**
 * Activity which shows the current order, allows user to place order and/or
 * remove menu items from said order.
 *
 * @author Francisco Marquez
 */
public class CurrentOrder extends AppCompatActivity {
    private Order order = Order.getInstance();
    private Button button;
    private ActivityCurrentOrderBinding binding;
    OrderAdapter adapter;

    /**
     * Initializes and binds all components of the activity.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_current_order);
        RecyclerView rcview = findViewById(R.id.rvOrder);
        ObservableArrayList<MenuItem> menuitems = order.getMenuItems();
        adapter = new OrderAdapter(this, order);
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
        button = findViewById(R.id.b_placeOrder);
        binding.setOrder(order);

    }

    /**
     * Adds the current order to the user's list of orders and redirects them to the
     * main activity.
     * @param v the view associated with the current order activity.
     */
    public void placeOrder(View v) {
        if (order.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
            alert.setTitle("No items in order");
            alert.setMessage("There are no items in the current order");
            alert.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = alert.create();
            dialog.show();
            return;
        }
        OrderList orders = OrderList.getInstance();
        orders.addOrder(new Order(order));
        order.reset();
        finish(); //makes this activity not backable
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "Order Added to List!", Toast.LENGTH_SHORT).show();
    }
}