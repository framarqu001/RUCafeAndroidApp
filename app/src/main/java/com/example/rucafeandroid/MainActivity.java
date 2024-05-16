package com.example.rucafeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import entities.Order;
import entities.OrderList;

/**
 * Main Activity of the RU Cafe app, allows users to order donuts, sandwiches and coffee,
 * as well as allowing them to edit/place their current order and edit/view the list of their
 * total orders.
 *
 * @author Ashley Berlinski, Francisco S. Marquez
 */
public class MainActivity extends AppCompatActivity {

    private Order currentOrder = Order.getInstance(); // SINGLETON DON'T USE GET THIS SINGLE ORDER
    private OrderList orderList = OrderList.getInstance();

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
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * On redefines the on start method, because why not..?
     */
    protected void onStart () {
        super.onStart();
    }

    /**
     * Navigates user to the coffee activity
     * @param view current view
     */
    public void showCoffee (View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates user to the donut activity
     * @param view current view
     */
    public void showDonut (View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates user to the sandwich activity
     * @param view current view
     */
    public void showSandwich (View view) {
        Intent intent = new Intent(this, SandwichActivity.class);
        startActivity(intent);
    }

    /**
     * Navigates user to the current order activity if there is any items in the
     * current order
     * @param view current view
     */
    public void showCurrentOrder (View view) {
        if (currentOrder.isEmpty()) {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("No items in order");
            alert.setMessage("There are no items in the current order");
            alert.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = alert.create();
            dialog.show();
            return;
        }
        Intent intent = new Intent(this, CurrentOrder.class);
        startActivity(intent);
    }

    /**
     * Navigates user to the all orders activity if there is any orders in the
     * order list
     * @param view current view
     */
    public void showAllOrders(View view) {
        if(orderList.isEmpty()){
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("No orders placed");
            alert.setMessage("There are no orders to display");
            alert.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
            AlertDialog dialog = alert.create();
            dialog.show();
            return;
        }
        Intent intent = new Intent(this, AllOrdersActivity.class);
        startActivity(intent);
    }
}
