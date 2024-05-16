package com.example.rucafeandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;

import entities.Order;
import entities.Sandwich;

/**
 * Activity which allows user to place an order for a sandwich
 *
 * @author Ashley Berlinski
 */
public class SandwichActivity extends AppCompatActivity {

    private Spinner spinner_bread, spinner_protein;
    private TextView tv_price;
    private Chip chip_cheese, chip_lettuce, chip_tomatoes, chip_onions;
    private Sandwich sandwich = new Sandwich();
    private Order order = Order.getInstance();

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
        setContentView(R.layout.activity_sandwich);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sandwich.setImage(R.drawable.sandwich_pic);

        tv_price = this.findViewById(R.id.textView_sandwich_price);
        chip_cheese = this.findViewById(R.id.chip_cheese);
        chip_lettuce = this.findViewById(R.id.chip_lettuce);
        chip_tomatoes = this.findViewById(R.id.chip_tomatoes);
        chip_onions = this.findViewById(R.id.chip_onions);
        setUpBreadSpinner();
        setUpProteinSpinner();
    }

    /**
     * Helper method used to set up spinner for bread selection
     */
    private void setUpBreadSpinner() {
        spinner_bread = this.findViewById(R.id.spinner_bread);
        ArrayAdapter<Sandwich.Bread> spinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Sandwich.Bread.values());
        spinner_bread.setAdapter(spinnerAdapter);
        spinner_bread.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sandwich.setBread((Sandwich.Bread) spinner_bread.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            //Blank
            }
        });
    }

    /**
     * Helper method used to set up spinner for protein selection
     */
    private void setUpProteinSpinner() {
        spinner_protein = this.findViewById(R.id.spinner_protein);
        ArrayAdapter<Sandwich.Protein> spinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, Sandwich.Protein.values());
        spinner_protein.setAdapter(spinnerAdapter);
        spinner_protein.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sandwich.setProtein((Sandwich.Protein) spinner_protein.getSelectedItem());
                tv_price.setText(sandwich.getPriceString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Blank
            }
        });
    }

    /**
     * Helper method used to add/remove corresponding add ons to a sandwich
     * @param addOn the add on to be removed/added.
     * @param checked flag that determines if add on will be added or removed from sandwich.
     */
    private void toggleAddOn(Sandwich.AddOn addOn, boolean checked) {
        if(checked)
            sandwich.addAddOn(addOn);
        else
            sandwich.removeAddOn(addOn);
        tv_price.setText(sandwich.getPriceString());
    }

    /**
     * Adds or removes cheese from sandwich.
     * @param v the view of the activity.
     */
    public void toggleCheese(View v){
        toggleAddOn(Sandwich.AddOn.CHEESE, chip_cheese.isChecked());
    }

    /**
     * Adds or removes tomatoes from sandwich.
     * @param v the view of the activity.
     */
    public void toggleTomatoes(View v){
        toggleAddOn(Sandwich.AddOn.TOMATOES, chip_tomatoes.isChecked());
    }

    /**
     * Adds or removes lettuce from sandwich.
     * @param v the view of the activity.
     */
    public void toggleLettuce(View v){
        toggleAddOn(Sandwich.AddOn.LETTUCE, chip_lettuce.isChecked());
    }

    /**
     * Adds or removes onions from sandwich.
     * @param v the view of the activity.
     */
    public void toggleOnions(View v){
        toggleAddOn(Sandwich.AddOn.ONIONS, chip_onions.isChecked());
    }

    /**
     * Adds the current sandwich to the order and redirects user to main activity.
     * @param v the view of the activity.
     */
    public void addSandwich(View v) {
        order.addToOrder(new Sandwich(sandwich));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, order.getLastMenuItem().toString(), Toast.LENGTH_SHORT).show();;
    }

}