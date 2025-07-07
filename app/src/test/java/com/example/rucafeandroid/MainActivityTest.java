package com.example.rucafeandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for MainActivity
 */
@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {

    @Mock
    private MainActivity mainActivity;

    @Mock
    private View mockView;

    @Before
    public void setUp() {
        // Set up any necessary mocks
        mainActivity = spy(new MainActivity());
        doNothing().when(mainActivity).startActivity(any(Intent.class));
    }

    /**
     * Test that the toast message "woah ok im doing something now" is displayed
     * when the coffee button is clicked
     */
    @Test
    public void testShowCoffeeDisplaysToast() {
        try (MockedStatic<Toast> mockedToast = mockStatic(Toast.class)) {
            // Mock the Toast.makeText() method
            Toast mockToast = mock(Toast.class);
            mockedToast.when(() -> Toast.makeText(any(), anyString(), anyInt())).thenReturn(mockToast);
            
            // Call the method
            mainActivity.showCoffee(mockView);
            
            // Verify that Toast.makeText was called with the correct message
            mockedToast.verify(() -> 
                Toast.makeText(
                    any(), 
                    eq("woah ok im doing something now"), 
                    eq(Toast.LENGTH_SHORT)
                ), 
                times(1)
            );
            
            // Verify that show() was called on the Toast
            verify(mockToast, times(1)).show();
        }
    }

    /**
     * Test that the toast message "woah ok im doing something now" is displayed
     * when the donut button is clicked
     */
    @Test
    public void testShowDonutDisplaysToast() {
        try (MockedStatic<Toast> mockedToast = mockStatic(Toast.class)) {
            // Mock the Toast.makeText() method
            Toast mockToast = mock(Toast.class);
            mockedToast.when(() -> Toast.makeText(any(), anyString(), anyInt())).thenReturn(mockToast);
            
            // Call the method
            mainActivity.showDonut(mockView);
            
            // Verify that Toast.makeText was called with the correct message
            mockedToast.verify(() -> 
                Toast.makeText(
                    any(), 
                    eq("woah ok im doing something now"), 
                    eq(Toast.LENGTH_SHORT)
                ), 
                times(1)
            );
            
            // Verify that show() was called on the Toast
            verify(mockToast, times(1)).show();
        }
    }

    /**
     * Test that the toast message "woah ok im doing something now" is displayed
     * when the sandwich button is clicked
     */
    @Test
    public void testShowSandwichDisplaysToast() {
        try (MockedStatic<Toast> mockedToast = mockStatic(Toast.class)) {
            // Mock the Toast.makeText() method
            Toast mockToast = mock(Toast.class);
            mockedToast.when(() -> Toast.makeText(any(), anyString(), anyInt())).thenReturn(mockToast);
            
            // Call the method
            mainActivity.showSandwich(mockView);
            
            // Verify that Toast.makeText was called with the correct message
            mockedToast.verify(() -> 
                Toast.makeText(
                    any(), 
                    eq("woah ok im doing something now"), 
                    eq(Toast.LENGTH_SHORT)
                ), 
                times(1)
            );
            
            // Verify that show() was called on the Toast
            verify(mockToast, times(1)).show();
        }
    }
}