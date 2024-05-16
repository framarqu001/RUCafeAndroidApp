package com.example.rucafeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import entities.Coffee;
import entities.Order;

/**
 * Activity which allows user to place an order for a coffee
 *
 * @author Francisco Marquez
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Coffee coffee = new Coffee();
    private Order order = Order.getInstance();
    private Button button;
    private Spinner spinner;
    private ArrayAdapter<Integer> adapter;

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
        setContentView(R.layout.activity_coffee);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        button = findViewById(R.id.button2);
        radioGroup.check(R.id.shortR);
        coffee.setImage(R.drawable.coffee);
        spinner = findViewById(R.id.coffeeQuantSpinner);
        Integer[] quantity = {1,2,3,4,5};
        adapter = new ArrayAdapter(
                this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantity);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.shortR) {
                coffee.setSize(Coffee.Size.SHORT);
            } else if (checkedId == R.id.tallR) {
                coffee.setSize(Coffee.Size.TALL);
            } else if (checkedId == R.id.grandeR) {
                coffee.setSize(Coffee.Size.GRANDE);
            } else if (checkedId == R.id.ventiR) {
                coffee.setSize(Coffee.Size.VENTI);
            }
            button.setText(coffee.getPriceString() + " Add to Order");
        });

    }

    /**
     * Sets quantity of the coffee depending on value of spinner.
     * @param parent The AdapterView where the selection happened
     * @param view The view within the AdapterView that was clicked
     * @param position The position of the view in the adapter
     * @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Integer quant = (Integer) spinner.getSelectedItem(); //get the selected item
        coffee.setQuantity(quant);
        button.setText(coffee.getPriceString() + " Add to Order");
    }

    /**
     * Defines behavior for spinner when nothing is selected.
     * @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected (AdapterView<?> parent) {
        //Nothing
    }

    /**
     * Adds/Removes the corresponding add-in depending on if it was checked or not.
     * @param v the view associated with the activity.
     */
    public void addIns(View v) {
        CheckBox cb = (CheckBox) v;
        int id = cb.getId();
        Coffee.AddIns addIn = null;
        if (id == R.id.checkBoxCaramel) {
           addIn = Coffee.AddIns.CARAMEL;
        } else if (id == R.id.checkBoxMocha){
           addIn = Coffee.AddIns.MOCHA;
        } else if (id == R.id.checkBoxSC) {
            addIn = Coffee.AddIns.SWEET_CREAM;
        } else if (id == R.id.checkBoxFV) {
           addIn = Coffee.AddIns.FRENCH_VANILLA;
        } else if (id == R.id.checkBoxIC) {
            addIn = Coffee.AddIns.IRISH_CREAM;
        }
        if (cb.isChecked()){
            coffee.addAddIn(addIn);
        } else {
            coffee.removeAddIn(addIn);
        }
        button.setText(coffee.getPriceString() + " Add to Order");
    }

    /**
     * Adds current coffee to the order.
     * @param v the view associated with the activity.
     */
    public void addCoffee(View v) {
        Coffee copy = new Coffee(coffee);
        order.addToOrder(copy);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, order.getLastMenuItem().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
}