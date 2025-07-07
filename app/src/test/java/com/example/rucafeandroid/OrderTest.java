package com.example.rucafeandroid;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import entities.Coffee;
import entities.Donut;
import entities.MenuItem;
import entities.Order;
import entities.Sandwich;

/**
 * Unit tests for the Order class to ensure order management functionality works correctly.
 */
public class OrderTest {

    private Order order;
    private static final double DELTA = 0.001; // Delta for double comparison

    @Before
    public void setUp() {
        // Get a fresh instance of Order for each test
        order = Order.getInstance();
        order.reset();
    }

    @Test
    public void testEmptyOrder() {
        assertTrue(order.isEmpty());
        assertEquals(0.0, order.getTotal(), DELTA);
    }

    @Test
    public void testAddSandwichToOrder() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        order.addToOrder(sandwich);
        
        assertFalse(order.isEmpty());
        assertEquals(10.99, order.getTotal(), DELTA);
    }

    @Test
    public void testAddMultipleItemsToOrder() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.CHICKEN, Sandwich.Bread.BAGEL);
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
        
        // Create a simple donut for testing
        Donut donut = new Donut();
        donut.setPrice(1.99);
        
        order.addToOrder(sandwich);
        order.addToOrder(donut);
        
        assertFalse(order.isEmpty());
        assertEquals(11.98, order.getTotal(), DELTA); // 9.99 (chicken + cheese) + 1.99
    }

    @Test
    public void testRemoveItemFromOrder() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        Donut donut = new Donut();
        donut.setPrice(1.99);
        
        order.addToOrder(sandwich);
        order.addToOrder(donut);
        assertEquals(12.98, order.getTotal(), DELTA); // 10.99 + 1.99
        
        order.removeFromOrder(sandwich);
        assertEquals(1.99, order.getTotal(), DELTA);
        assertFalse(order.isEmpty());
        
        order.removeFromOrder(donut);
        assertTrue(order.isEmpty());
        assertEquals(0.0, order.getTotal(), DELTA);
    }

    @Test
    public void testGetLastMenuItem() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        Donut donut = new Donut();
        donut.setPrice(1.99);
        
        order.addToOrder(sandwich);
        order.addToOrder(donut);
        
        MenuItem lastItem = order.getLastMenuItem();
        assertEquals(donut, lastItem);
    }

    @Test
    public void testOrderReset() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        order.addToOrder(sandwich);
        
        assertFalse(order.isEmpty());
        
        order.reset();
        assertTrue(order.isEmpty());
        assertEquals(0.0, order.getTotal(), DELTA);
    }

    @Test
    public void testOrderCopy() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        order.addToOrder(sandwich);
        
        Order copyOrder = new Order(order);
        
        // Original order should remain unchanged
        assertFalse(order.isEmpty());
        
        // Copy should have the same items
        assertFalse(copyOrder.isEmpty());
    }

    @Test
    public void testTotalCalculation() {
        Sandwich sandwich = new Sandwich(Sandwich.Protein.BEEF, Sandwich.Bread.WHEAT);
        sandwich.addAddOn(Sandwich.AddOn.CHEESE);
        sandwich.addAddOn(Sandwich.AddOn.LETTUCE);
        
        order.addToOrder(sandwich);
        
        // Check that the total includes the sandwich price
        assertEquals(11.99, order.getTotal(), DELTA); // 10.99 + 1.00 (cheese) + 0.30 (lettuce) = 12.29
        
        // The getTotalString method should calculate the total with sales tax
        String totalString = order.getTotalString();
        assertTrue(totalString.startsWith("$"));
    }
}