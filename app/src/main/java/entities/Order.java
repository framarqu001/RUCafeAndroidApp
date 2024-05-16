package entities;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import java.text.DecimalFormat;

/**
 * Order class represents a unique order comprised of menu Items in RUCafe.
 * User can add and remove menu items from an order.
 * @author Ashley Berlinski, Francisco Marquez
 */
public final class Order { // SINGLETON CLASS GLOBAL ACCESS
    private static Order globalOrder; //single order instance
    private static int TOTAL_ORDERS = 0;
    private ObservableArrayList<MenuItem> menuItems;
    private int orderNumber;
    private double total;
    private ObservableField<String> subTotalBind;
    private ObservableField<String> totalBind;
    private ObservableField<String> salesTaxBind;
    private final double SALES_TAX =.06625;

    /**
     * Private Order Constructor so that we can control when order is constructed.
     */
    private Order () {
        this.menuItems = new ObservableArrayList<>();
        subTotalBind = new ObservableField<>();
        totalBind = new ObservableField<>();
        salesTaxBind = new ObservableField<>();
    }

    /**
     * Returns a static instance of an order to be used throughout the program
     * @return a static instance of order.
     */
    public static synchronized Order getInstance () {
        if (globalOrder == null)
            globalOrder = new Order();
        return globalOrder;
    }

    /**
     * Copy constructor for an Order.
     * This constructor is used to pass an order from OrderController Class to an OrderList object.
     * @param copyOrder the copyOrder to be cloned.
     */
    public Order(Order copyOrder) {
        this.orderNumber = ++TOTAL_ORDERS;
        this.total = copyOrder.total;
        this.subTotalBind = new ObservableField<>();
        this.subTotalBind.set(copyOrder.subTotalBind.get());
        this.menuItems = new ObservableArrayList<>();
        if (copyOrder.menuItems != null) {
            for (MenuItem item : copyOrder.menuItems) {
                menuItems.add(item.clone());
            }
        }
    }

    /**
     * Calculates the current total of the order and sets the order's total to that value.
     */
    private void calculateTotal() {
        double price = 0;
        for (MenuItem item: menuItems){
            price += item.getPrice();
        }
        total = price * (1 + SALES_TAX);
    }


    /**
     * Adds a menu Item to the order and increments the menu items price to the total.
     * @param menuItem Menu item to be added to the order.
     */
    public void addToOrder(MenuItem menuItem) {
        menuItems.add(menuItem);
        total += menuItem.getPrice();
        setPriceStrings();
    }

    /**
     * removes a menu Item from order and decrements the menu items price from the order total.
     * @param menuItem Menu item to be removed from the order.
     */
    public void removeFromOrder(MenuItem menuItem) {
        total -= menuItem.getPrice();
        menuItems.remove(menuItem);
        setPriceStrings();
    }



    /**
     * Helper method to clone an order. Creates cloned Menu Items to be added to a cloned Order.
     * @param menuItems Menu Items to be cloned.
     * @return a new observable list of menu items with cloned menu items.
     */
    private static ObservableArrayList<MenuItem> copyMenuItems(ObservableArrayList<MenuItem> menuItems) {
        ObservableArrayList<MenuItem> copy = new ObservableArrayList<>();
        for (MenuItem item : menuItems) {
            copy.add(item.clone());
        }
        return copy;
    }

    /**
     * Determines if order has any menu items or not.
     * @return true if the order contains no menu items, false otherwise.
     */
    public boolean isEmpty() {
        return menuItems.isEmpty();
    }

    /**
     * Resets an Order to its default values when constructed.
     */
    public void reset() {
        menuItems.clear();
        total = 0;

    }

    /**
     * Returns the subtotal of the order.
     * @return the subtotal of the order.
     */
    public ObservableField<String> getSubTotalBind () {
        return subTotalBind;
    }

    /**
     * Returns the total of the order.
     * @return the total of the order.
     */
    public ObservableField<String> getTotalBind () {
        return totalBind;
    }

    /**
     * Returns the sale tax applied to the order.
     * @return the sale tax of the order.
     */
    public ObservableField<String> getSalesTaxBind () {
        return salesTaxBind;
    }

    /**
     * Calculates the total, sub total and sales tax of the order and
     * sets each corresponding observable field to that value.
     */
    private void setPriceStrings() {
        DecimalFormat dcFormat = new DecimalFormat("###.00");

        String string = "$" + dcFormat.format(total);
        this.subTotalBind.set(string);

        string = "$" + dcFormat.format(total * (1 + SALES_TAX));
        this.totalBind.set(string);

        string = "$" + dcFormat.format(total * SALES_TAX);
        this.salesTaxBind.set(string);

    }

    /**
     * Returns the last menu item in the order.
     * @return the last menu item in the order.
     */
    public MenuItem getLastMenuItem() {
        int decrement = 1;
        return menuItems.get(menuItems.size() - decrement);
    }

    /**
     * This method allows binding between JavaFX elements and Order's menu items.
     * @return The observable array list of Menu Items.
     */
    public ObservableArrayList<MenuItem> getMenuItems () {
        return menuItems;
    }

    /**
     * Returns the ID number of the Order.
     * @return the ID number of the Order.
     */
    public int getID () {
        return TOTAL_ORDERS;
    }

    /**
     * Returns the total of the order.
     * @return the total of the order.
     */
    public double getTotal () {
        return total;
    }

    /**
     * Returns a formatted string representing the total of the order.
     * @return a formatted string representing the total of the order.
     */
    public String getTotalString() {
        calculateTotal();
        DecimalFormat dcFormat = new DecimalFormat("###.00");
        return "$" + dcFormat.format(total);
    }

    /**
     * Returns a formatted String of an order only showing its order number.
     * @return Formatted String of an order only showing its order number.
     */
    @Override
    public String toString () {
        return  orderNumber + "";
    }
}
