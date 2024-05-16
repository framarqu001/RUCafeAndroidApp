package com.example.rucafeandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import entities.Order;
import entities.OrderList;

/**
 * Activity which shows the list of orders that the user has placed, allows
 * user to view them and remove the selected one from list.
 *
 * @author Ashley Berlinski, Francisco Marquez
 */
public class AllOrdersActivity extends AppCompatActivity {

    private OrderList orders = OrderList.getInstance();
    private Spinner spinner;
    private TextView tv_price;
    private RecyclerView rcview;
    private Context context;
    ArrayAdapter<Order> spinnerAdapter;
    AllOrdersAdapter adapter;


    /**
     * Initializes and binds all components of the activity.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_orders);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        context = this;
        tv_price = findViewById(R.id.textView_order_price);
        rcview = findViewById(R.id.rvCurrentOrder);
        adapter = new AllOrdersAdapter(this, orders.getFirstOrder());
        rcview.setAdapter(adapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
        setUpSpinner();
    }

    /**
     * Helper method used to set up spinner for order list.
     */
    private void setUpSpinner() {
        spinner = findViewById(R.id.spinner_order);
        spinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, orders.getOrderList());
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("HERE", "onItemSelected: HERE");
                updateRV();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Removes the currently selected order when button is pressed.
     * @param v the view associated with the activity.
     */
    public void removeOrder(View v) {
        Order removeOrder = (Order) spinner.getSelectedItem();
        int position = spinner.getSelectedItemPosition();
        orders.removeOrder(removeOrder);
        int size = orders.getOrderList().size();
        spinnerAdapter.notifyDataSetChanged();

        if (noOrders(v)) return;

        spinner.setSelection(size -1); // selects last item

        if (position == size - 1)  //listener does not activate if index remains the same
            updateRV();
    }

    /**
     * Helper method used to update the recyclerview when a new order is selected.
     */
    private void updateRV() {
        Order order = (Order) spinner.getSelectedItem();
        tv_price.setText(order.getTotalString());
        adapter.updateOrder(order);
        adapter.notifyDataSetChanged();
    }

    /**
     * Helper method which checks if the order list is empty, if so redirects the user to the
     * main activity.
     * @param v the view associated with the activity.
     * @return true if there are no orders in the list, false if otherwise.
     */
    private boolean noOrders(View v) {
        if (orders.isEmpty()) {
            adapter.clear();
            AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
            alert.setTitle("No Orders");
            alert.setMessage("All orders have been removed");
            alert.setPositiveButton("OK", (dialog, which) -> {
                dialog.dismiss();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            });
            AlertDialog dialog = alert.create();
            dialog.show();
            return true;
        }
        return false;
    }


}

