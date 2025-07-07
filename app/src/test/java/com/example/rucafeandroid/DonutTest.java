package com.example.rucafeandroid;

import org.junit.Test;
import static org.junit.Assert.*;

import entities.Donut;
import entities.MenuItem;

/**
 * Unit tests for the Donut class to ensure price calculation is accurate.
 * Tests various combinations of types, flavors, and quantities.
 */
public class DonutTest {

    private static final double DELTA = 0.001; // Delta for double comparison

    @Test
    public void testYeastDonut() {
        Donut donut = new Donut(Donut.Type.YEAST, Donut.Flavor.GLAZED, 1, 0);
        assertEquals(1.79, donut.price(), DELTA);
    }

    @Test
    public void testCakeDonut() {
        Donut donut = new Donut(Donut.Type.CAKE, Donut.Flavor.VANILLA, 1, 0);
        assertEquals(1.89, donut.price(), DELTA);
    }

    @Test
    public void testDonutHole() {
        Donut donut = new Donut(Donut.Type.HOLE, Donut.Flavor.CHOCOLATE, 1, 0);
        assertEquals(0.39, donut.price(), DELTA);
    }

    @Test
    public void testMultipleYeastDonuts() {
        Donut donut = new Donut(Donut.Type.YEAST, Donut.Flavor.JELLY, 3, 0);
        assertEquals(5.37, donut.price(), DELTA); // 1.79 * 3
    }

    @Test
    public void testMultipleCakeDonuts() {
        Donut donut = new Donut(Donut.Type.CAKE, Donut.Flavor.FUNFETTI, 2, 0);
        assertEquals(3.78, donut.price(), DELTA); // 1.89 * 2
    }

    @Test
    public void testMultipleDonutHoles() {
        Donut donut = new Donut(Donut.Type.HOLE, Donut.Flavor.POWDERED, 5, 0);
        assertEquals(1.95, donut.price(), DELTA); // 0.39 * 5
    }

    @Test
    public void testChangeDonutType() {
        Donut donut = new Donut(Donut.Type.YEAST, Donut.Flavor.GLAZED, 1, 0);
        assertEquals(1.79, donut.price(), DELTA);
        
        donut.setType(Donut.Type.CAKE);
        assertEquals(1.89, donut.price(), DELTA);
        
        donut.setType(Donut.Type.HOLE);
        assertEquals(0.39, donut.price(), DELTA);
    }

    @Test
    public void testChangeDonutQuantity() {
        Donut donut = new Donut(Donut.Type.CAKE, Donut.Flavor.PINK_FROSTED, 1, 0);
        assertEquals(1.89, donut.price(), DELTA);
        
        donut.setQuantity(2);
        assertEquals(3.78, donut.price(), DELTA); // 1.89 * 2
        
        donut.setQuantity(4);
        assertEquals(7.56, donut.price(), DELTA); // 1.89 * 4
    }

    @Test
    public void testCloneDonut() {
        Donut original = new Donut(Donut.Type.YEAST, Donut.Flavor.BOSTON_CREAM, 2, 123);
        Donut clone = (Donut) original.clone();
        
        assertEquals(original.price(), clone.price(), DELTA);
        assertEquals(original.getImage(), clone.getImage());
        
        // Modifying clone should not affect original
        clone.setQuantity(3);
        assertEquals(3.58, original.price(), DELTA); // 1.79 * 2
        assertEquals(5.37, clone.price(), DELTA);    // 1.79 * 3
    }

    @Test
    public void testIncompleteDonut() {
        Donut donut = new Donut(Donut.Type.YEAST, null, 1, 0);
        assertTrue(donut.isIncomplete());
        
        donut.setFlavor(Donut.Flavor.GLAZED);
        assertFalse(donut.isIncomplete());
    }

    @Test
    public void testGetDonutText() {
        Donut yeastDonut = new Donut(Donut.Type.YEAST, Donut.Flavor.JELLY, 1, 0);
        assertEquals("jelly yeast donut", yeastDonut.getDonutText());
        
        Donut cakeDonut = new Donut(Donut.Type.CAKE, Donut.Flavor.VANILLA, 1, 0);
        assertEquals("vanilla cake donut", cakeDonut.getDonutText());
        
        Donut holeDonut = new Donut(Donut.Type.HOLE, Donut.Flavor.CHOCOLATE, 1, 0);
        assertEquals("chocolate donut hole", holeDonut.getDonutText());
    }

    @Test
    public void testToString() {
        Donut donut = new Donut(Donut.Type.YEAST, Donut.Flavor.APPLE_CIDER, 3, 0);
        assertEquals("apple cider yeast donut - quantity: (3)", donut.toString());
        
        Donut incompleteDonut = new Donut(Donut.Type.CAKE, null, 1, 0);
        assertEquals("incomplete donut", incompleteDonut.toString());
    }

    @Test
    public void testGetFlavorsForTypes() {
        Donut.Flavor[] yeastFlavors = Donut.Type.YEAST.getFlavors();
        assertEquals(6, yeastFlavors.length);
        
        Donut.Flavor[] cakeFlavors = Donut.Type.CAKE.getFlavors();
        assertEquals(3, cakeFlavors.length);
        
        Donut.Flavor[] holeFlavors = Donut.Type.HOLE.getFlavors();
        assertEquals(3, holeFlavors.length);
    }
}