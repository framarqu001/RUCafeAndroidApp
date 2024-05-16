package entities;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

/**
 * The OrderList class contains a list of all the orders placed in RUCafe.
 *
 * @author Ashley Berlinski, Francisco Marquez
 */
public final class OrderList {
    private static OrderList globalOrderList;
    ObservableList<Order> orderList;

    /**
     * Constructs an OrderList with a new Observable Array List.
     */
    private OrderList () {
        this.orderList = new ObservableArrayList<>();
    }

    /**
     * Returns a static instance of an order list to be used throughout the program
     * @return a static instance of an order list
     */
    public static synchronized OrderList getInstance () {
        if (globalOrderList == null)
            globalOrderList = new OrderList();
        return globalOrderList;
    }

    /**
     * Returns the first order in the list.
     * @return the first order in the list, null if it is empty.
     */
    public Order getFirstOrder() {
        if(!(orderList.isEmpty()))
            return orderList.get(0);
        else
            return null;
    }

    /**
     * Removes an order from the list.
     * @param order order to be removed.
     */
    public void removeOrder (Order order) {

        orderList.remove(order);
    }

    /**
     * Adds an order to the list.
     * @param order order to be added.
     */
    public void addOrder (Order order) {

        orderList.add(order);
    }

    /**
     * Returns The list of orders held by OrderList
     * @return The list of orders held by OrderList
     */
    public ObservableList<Order> getOrderList () {
        return orderList;
    }

    /**
     * Determines if the order list has any orders.
     * @return True if order list is empty, false otehrwise.
     */
    public boolean isEmpty () {
        return orderList.isEmpty();
    }

    /**
     * Formats each order in the list to display the order #, details of each menu item, and subtotal.
     * @return A formatted String to be written to a file.
     */
    public String printOrders() {
        String string = "";
        for (Order order : orderList) {
            string += "Order: #" + order + "\n";
                for (MenuItem menuItem : order.getMenuItems()) {
                    string += "\t" + menuItem + "\n";
                }
                string += "\t" + "Sub-Total: " + order.getTotal() + "\n\n";
        }
        return string;
    }

}
