package com.example.rucafeandroid;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import entities.Donut;

/**
 * Activity which allows user to place an order for a donut
 *
 * @author Ashley Berlinski
 */
public class DonutActivity extends AppCompatActivity {

    private RecyclerView rcview;

    private ArrayList<Donut> donuts = new ArrayList<>();
    private int [] yeastImages = {R.drawable.chocolate, R.drawable.powdered, R.drawable.glazed,
        R.drawable.jelly, R.drawable.apple_cider, R.drawable.boston_cream};
    private int [] cakeImages = {R.drawable.vanilla, R.drawable.pink_frosted, R.drawable.funfetti};
    private int [] holeImages = {R.drawable.chocolate_hole, R.drawable.powdered_hole,
        R.drawable.glazed_hole};

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
        setContentView(R.layout.activity_donut);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setRcview();
    }

    /**
     * Helper method used to initialize the recycler view.
     */
    private void setRcview(){
        rcview = findViewById(R.id.rcViewDonut_menu);
        setupFlavorItems();
        DonutAdapter donutAdapter = new DonutAdapter(this, donuts);
        rcview.setAdapter(donutAdapter);
        rcview.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * Helper method used to populate dataset (list of all donuts) in the recycler view
     */
    private void setupFlavorItems(){
        //Get all donut type values
        Donut.Type [] types = Donut.Type.values();
        for(Donut.Type type: types) { //For each type, get the list of flavors and add them to donuts
            Donut.Flavor [] flavors = type.getFlavors();
            int [] flavorImages;
            int startQuantity = 1, index = 0;

            switch(type) {
                case YEAST:
                    flavorImages = yeastImages;
                    break;
                case CAKE:
                    flavorImages = cakeImages;
                    break;
                default:
                    flavorImages = holeImages;
                    break;
            }

            for (Donut.Flavor flavor: flavors) {
                donuts.add(new Donut(type, flavor, startQuantity, flavorImages[index++]));
            }
        }
    }

}