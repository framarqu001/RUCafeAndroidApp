package com.example.rucafeandroid;

import org.junit.Test;
import static org.junit.Assert.*;

import entities.Sandwich;

/**
 * Unit tests for the Sandwich class to ensure price calculation is accurate.
 * Tests various combinations of proteins, breads, and add-ons.
 */
public class SandwichTest {

    private static final double DELTA = 0.001; // Delta for double comparison

    @Test
    public void testBeefSandwichNoAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        assertEquals(10.99, sandwich.price(), DELTA);
    }

    @Test
    public void testChickenSandwichNoAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.BAGEL);
        assertEquals(8.99, sandwich.price(), DELTA);
    }

    @Test
    public void testFishSandwichNoAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.FISH, Sandwich.Bread.SOURDOUGH);
        assertEquals(9.99, sandwich.price(), DELTA);
    }

    @Test
    public void testSandwichWithCheese() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
        assertEquals(11.99, sandwich.price(), DELTA); // 10.99 + 1.00
    }

    @Test
    public void testSandwichWithLettuce() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.BAGEL);
        sandwich.addAddOn(Sandwich.AddOn.LETTUCE);
        assertEquals(9.29, sandwich.price(), DELTA); // 8.99 + 0.30
    }

    @Test
    public void testSandwichWithMultipleAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.FISH, Sandwich.Bread.SOURDOUGH);
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
        sandwich.addAddOn(Sandwich.AddOn.LETTUCE);
        sandwich.addAddOn(Sandwich.AddOn.TOMATOES);
        assertEquals(11.59, sandwich.price(), DELTA); // 9.99 + 1.00 + 0.30 + 0.30
    }

    @Test
    public void testSandwichWithAllAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
        sandwich.addAddOn(Sandwich.AddOn.LETTUCE);
        sandwich.addAddOn(Sandwich.AddOn.TOMATOES);
        sandwich.addAddOn(Sandwich.AddOn.ONIONS);
        assertEquals(12.89, sandwich.price(), DELTA); // 10.99 + 1.00 + 0.30 + 0.30 + 0.30
    }

    @Test
    public void testAddAndRemoveAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.BAGEL);
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
        sandwich.addAddOn(Sandwich.AddOn.LETTUCE);
        assertEquals(10.29, sandwich.price(), DELTA); // 8.99 + 1.00 + 0.30
        
        sandwich.removeAddOn(Sandwich.AddOn.CHEESE);
        assertEquals(9.29, sandwich.price(), DELTA); // 8.99 + 0.30
    }

    @Test
    public void testAddDuplicateAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.FISH, Sandwich.Bread.SOURDOUGH);
        assertTrue(sandwich.addAddOn(Sandwich.AddOn.CHEESE)); // First time should succeed
        assertFalse(sandwich.addAddOn(Sandwich.AddOn.CHEESE)); // Second time should fail
        assertEquals(10.99, sandwich.price(), DELTA); // 9.99 + 1.00 (only once)
    }

    @Test
    public void testAddMultipleAddOnsAtOnce() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        sandwich.addAddOns(new Sandwich.AddOn[]{
                Sandwich.AddOn.CHEESE, 
                Sandwich.AddOn.LETTUCE, 
                Sandwich.AddOn.TOMATOES
        });
        assertEquals(12.59, sandwich.price(), DELTA); // 10.99 + 1.00 + 0.30 + 0.30
    }

    @Test
    public void testRemoveMultipleAddOnsAtOnce() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.BAGEL);
        sandwich.addAddOns(new Sandwich.AddOn[]{
                Sandwich.AddOn.CHEESE, 
                Sandwich.AddOn.LETTUCE, 
                Sandwich.AddOn.TOMATOES, 
                Sandwich.AddOn.ONIONS
        });
        assertEquals(10.89, sandwich.price(), DELTA); // 8.99 + 1.00 + 0.30 + 0.30 + 0.30
        
        sandwich.removeAddOns(new Sandwich.AddOn[]{
                Sandwich.AddOn.LETTUCE, 
                Sandwich.AddOn.TOMATOES
        });
        assertEquals(10.29, sandwich.price(), DELTA); // 8.99 + 1.00 + 0.30 (onions)
    }

    @Test
    public void testCloneSandwich() {
        Sandwich original = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        original.addAddOn(Sandwich.AddOn.CHEESE);
        original.addAddOn(Sandwich.AddOn.LETTUCE);
        
        Sandwich clone = (Sandwich) original.clone();
        assertEquals(original.price(), clone.price(), DELTA);
        
        // Modifying clone should not affect original
        clone.addAddOn(Sandwich.AddOn.TOMATOES);
        assertEquals(11.99, original.price(), DELTA); // 10.99 + 1.00 + 0.30
        assertEquals(12.29, clone.price(), DELTA);    // 10.99 + 1.00 + 0.30 + 0.30
    }

    @Test
    public void testIncompleteCheck() {
        Sandwich incomplete = new Sandwich();
        assertTrue(incomplete.isIncomplete());
        
        incomplete.setProtein(Sandwich.Protein.BEEF);
        assertTrue(incomplete.isIncomplete()); // Still incomplete without bread
        
        incomplete.setBread(Sandwich.Bread.WHEAT);
        assertFalse(incomplete.isIncomplete()); // Now complete
    }

    @Test
    public void testToString() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.SOURDOUGH);
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
        sandwich.addAddOn(Sandwich.AddOn.TOMATOES);
        
        String expected = "chicken sandwich with sourdough bread and add-ons: (cheese, tomatoes)";
        assertEquals(expected, sandwich.toString());
    }

    @Test
    public void testToStringNoAddOns() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.FISH, Sandwich.Bread.BAGEL);
        
        String expected = "fish sandwich with bagel bread and add-ons: (none)";
        assertEquals(expected, sandwich.toString());
    }
}