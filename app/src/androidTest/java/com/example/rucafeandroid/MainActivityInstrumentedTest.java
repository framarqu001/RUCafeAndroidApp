package com.example.rucafeandroid;

import android.view.View;
import android.widget.Button;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test for MainActivity
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    /**
     * Test that clicking the coffee button displays the toast message
     */
    @Test
    public void testCoffeeButtonDisplaysToast() {
        // Click on the coffee button
        Espresso.onView(ViewMatchers.withId(R.id.coffeeButton))
                .perform(ViewActions.click());

        // Check that the toast with the correct message is displayed
        Espresso.onView(ViewMatchers.withText("woah ok im doing something now"))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.not(Matchers.is(activityRule.getScenario().getResult().getResultData()))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test that clicking the donut button displays the toast message
     */
    @Test
    public void testDonutButtonDisplaysToast() {
        // Click on the donut button
        Espresso.onView(ViewMatchers.withId(R.id.donutButton))
                .perform(ViewActions.click());

        // Check that the toast with the correct message is displayed
        Espresso.onView(ViewMatchers.withText("woah ok im doing something now"))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.not(Matchers.is(activityRule.getScenario().getResult().getResultData()))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    /**
     * Test that clicking the sandwich button displays the toast message
     */
    @Test
    public void testSandwichButtonDisplaysToast() {
        // Click on the sandwich button
        Espresso.onView(ViewMatchers.withId(R.id.sandwichButton))
                .perform(ViewActions.click());

        // Check that the toast with the correct message is displayed
        Espresso.onView(ViewMatchers.withText("woah ok im doing something now"))
                .inRoot(RootMatchers.withDecorView(
                        Matchers.not(Matchers.is(activityRule.getScenario().getResult().getResultData()))))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}