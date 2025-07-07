package com.example.rucafeandroid;

import org.junit.Test;
import static org.junit.Assert.*;

import entities.Coffee;
import entities.MenuItem;

/**
 * Unit tests for the Coffee class to ensure price calculation is accurate.
 * Tests various combinations of sizes, add-ins, and quantities.
 */
public class CoffeeTest {

    private static final double DELTA = 0.001; // Delta for double comparison

    @Test
    public void testDefaultCoffee() {
        Coffee coffee = new Coffee();
        assertEquals(1.99, coffee.price(), DELTA); // Default SHORT size, no add-ins, quantity 1
    }

    @Test
    public void testCoffeeSizes() {
        Coffee coffee = new Coffee();
        
        coffee.setSize(Coffee.Size.SHORT);
        assertEquals(1.99, coffee.price(), DELTA);
        
        coffee.setSize(Coffee.Size.TALL);
        assertEquals(2.49, coffee.price(), DELTA);
        
        coffee.setSize(Coffee.Size.GRANDE);
        assertEquals(2.99, coffee.price(), DELTA);
        
        coffee.setSize(Coffee.Size.VENTI);
        assertEquals(3.49, coffee.price(), DELTA);
    }

    @Test
    public void testCoffeeWithOneAddIn() {
        Coffee coffee = new Coffee();
        coffee.addAddIn(Coffee.AddIns.SWEET_CREAM);
        assertEquals(2.29, coffee.price(), DELTA); // 1.99 + 0.30
    }

    @Test
    public void testCoffeeWithMultipleAddIns() {
        Coffee coffee = new Coffee();
        coffee.addAddIn(Coffee.AddIns.SWEET_CREAM);
        coffee.addAddIn(Coffee.AddIns.CARAMEL);
        coffee.addAddIn(Coffee.AddIns.MOCHA);
        assertEquals(2.89, coffee.price(), DELTA); // 1.99 + 0.30 + 0.30 + 0.30
    }

    @Test
    public void testCoffeeWithAllAddIns() {
        Coffee coffee = new Coffee();
        coffee.addAddIn(Coffee.AddIns.SWEET_CREAM);
        coffee.addAddIn(Coffee.AddIns.FRENCH_VANILLA);
        coffee.addAddIn(Coffee.AddIns.IRISH_CREAM);
        coffee.addAddIn(Coffee.AddIns.CARAMEL);
        coffee.addAddIn(Coffee.AddIns.MOCHA);
        assertEquals(3.49, coffee.price(), DELTA); // 1.99 + 5*0.30
    }

    @Test
    public void testAddDuplicateAddIns() {
        Coffee coffee = new Coffee();
        assertTrue(coffee.addAddIn(Coffee.AddIns.SWEET_CREAM)); // First time should succeed
        assertFalse(coffee.addAddIn(Coffee.AddIns.SWEET_CREAM)); // Second time should fail
        assertEquals(2.29, coffee.price(), DELTA); // 1.99 + 0.30 (only once)
    }

    @Test
    public void testRemoveAddIn() {
        Coffee coffee = new Coffee();
        coffee.addAddIn(Coffee.AddIns.SWEET_CREAM);
        coffee.addAddIn(Coffee.AddIns.CARAMEL);
        assertEquals(2.59, coffee.price(), DELTA); // 1.99 + 0.30 + 0.30
        
        coffee.removeAddIn(Coffee.AddIns.SWEET_CREAM);
        assertEquals(2.29, coffee.price(), DELTA); // 1.99 + 0.30
    }

    @Test
    public void testCoffeeQuantity() {
        Coffee coffee = new Coffee();
        coffee.setSize(Coffee.Size.TALL);
        coffee.addAddIn(Coffee.AddIns.CARAMEL);
        assertEquals(2.79, coffee.price(), DELTA); // 2.49 + 0.30
        
        coffee.setQuantity(2);
        assertEquals(5.58, coffee.price(), DELTA); // (2.49 + 0.30) * 2
        
        coffee.setQuantity(3);
        assertEquals(8.37, coffee.price(), DELTA); // (2.49 + 0.30) * 3
    }

    @Test
    public void testCloneCoffee() {
        Coffee original = new Coffee();
        original.setSize(Coffee.Size.GRANDE);
        original.addAddIn(Coffee.AddIns.MOCHA);
        original.setQuantity(2);
        
        Coffee clone = (Coffee) original.clone();
        assertEquals(original.price(), clone.price(), DELTA);
        
        // Modifying clone should not affect original
        clone.addAddIn(Coffee.AddIns.CARAMEL);
        assertEquals(6.58, original.price(), DELTA); // (2.99 + 0.30) * 2
        assertEquals(7.18, clone.price(), DELTA);    // (2.99 + 0.30 + 0.30) * 2
    }

    @Test
    public void testToString() {
        Coffee coffee = new Coffee();
        coffee.setSize(Coffee.Size.VENTI);
        coffee.addAddIn(Coffee.AddIns.FRENCH_VANILLA);
        coffee.addAddIn(Coffee.AddIns.CARAMEL);
        
        String result = coffee.toString();
        assertTrue(result.contains("Venti"));
        assertTrue(result.contains("French_vanilla"));
        assertTrue(result.contains("Caramel"));
    }

    @Test
    public void testToStringNoAddIns() {
        Coffee coffee = new Coffee();
        coffee.setSize(Coffee.Size.TALL);
        
        String result = coffee.toString();
        assertTrue(result.contains("Tall"));
        assertTrue(result.contains("None"));
    }
}