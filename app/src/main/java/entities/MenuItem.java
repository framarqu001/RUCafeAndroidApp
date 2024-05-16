package entities;

import java.text.DecimalFormat;

/**
 * The Menu class is an abstract class for all items in the RU Cafe.
 * All menu items must implement the abstract method price().
 *
 * @author Francisco Marquez
 */
abstract public class MenuItem {
    double price;
    int image;

    /**
     * Default constructor for Menu Item.
     */
    public MenuItem () {
    }

    /**
     * Clone constructor for Menu Item.
     * @param menuItem Menu Item to be cloned
     */
    public MenuItem(MenuItem menuItem) {
        this.price = menuItem.price;
    }

    /**
     * Abstract method that determines the price of a menu item.
     * @return price of the item.
     */
    public abstract double price();

    /**
     * Method used to make a copy of the current menu item, should be overwritten by any child class
     * that wishes to use this method.
     * @return null since the child classes should override this method.
     */
    @Override
    public MenuItem clone() {
        return null;}

    /**
     * Returns The price of a menu Item.
     * @return The price of a menu Item.
     */
    public double getPrice () {
        return price;
    }

    /**
     * Sets the price of a menu item and updates string property.
     * @param newPrice Price to be set to.
     */
    public void setPrice (double newPrice) {
        price = newPrice;
    }

    /**
     * Returns a String value of the menu items priceStringProperty.
     * @return A String value of the menu items priceStringProperty.
     */
    public String getPriceString () {
        return "$" + new DecimalFormat("###.00").format(getPrice());
    }

    /**
     * Returns a formatted String reflecting the menu item's price in format $xx.xx.
     * @return A formatted String reflecting the menu item's price in format $xx.xx.
     */
    @Override
    public String toString() {
        return "$" + new DecimalFormat("###.00").format(getPrice());
    }

    /**
     * Returns the associated image id with the menu item.
     * @return the associated image id with the menu item.
     */
    public int getImage () {
        return image;
    }

    /**
     * Sets the image id of the menu item.
     * @param image the image to be set as the image for the menu item.
     */
    public void setImage (int image) {
        this.image = image;
    }
}
