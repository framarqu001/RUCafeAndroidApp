package entities;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

/**
 * The Coffee class represents a coffee as part of an order of Menu Items.
 * A coffee has to have a size of Short, Tall, Grande, or Venti.
 * A coffee can also multiple add-ins, with a .30 cent surcharge per add-in.
 *
 * @author Francisco Marquez
 */
public class Coffee extends MenuItem {
    /**
     * Size Enum represents the different sizes a coffee can have and the associated price.
     */
    public enum Size {
        SHORT(1.99), TALL(2.49), GRANDE(2.99), VENTI(3.49);

        double price;

        /**
         * Constructs a Size Enum
         * @param price Price for a coffee Size.
         */
        Size(double price) {
            this.price = price;
        }

        /**
         * Returns A formatted Enum string that capitalizes the first letter.
         * @return A formatted Enum string that capitalizes the first letter.
         */
        @Override
        public String toString () {
            String string = name().toLowerCase();
            return string.substring(0,1).toUpperCase() + string.substring(1);
        }

        /**
         * Returns the price of a Size Enum.
         * @return the price of a Size Enum.
         */
        public double getPrice() {
            return price;
        }
    }

    /**
     * AddIns Enum represents the different add-ins a coffee can have and the associated price.
     */
    public enum AddIns {
        SWEET_CREAM(.30), FRENCH_VANILLA(.30), IRISH_CREAM(.30), CARAMEL(.30), MOCHA(.30);

        double price;
        /**
         * Constructs a AddIns Enum
         * @param price Price for a coffee add-in.
         */
        AddIns(double price) {
            this.price = price;
        }

        /**
         * Returns A formatted Enum string that capitalizes the first letter.
         * @return A formatted Enum string that capitalizes the first letter.
         */
        @Override
        public String toString () {
            String string = name().toLowerCase();
            return string.substring(0,1).toUpperCase() + string.substring(1);
        }

        /**
         * Returns the price of a AddIns Enum.
         * @return the price of a AddIns Enum.
         */
        public double getPrice() {
            return price;
        }
    }

    private Size size;
    private ObservableList<AddIns> addIns;
    private int quantity;


    /**
     * Constructs a Coffee Object.
     * The default size is Short, no add-ins, and quantity of 1.
     */
    public Coffee() {
        this.addIns = new ObservableArrayList<>();
        quantity = 1;
        size = Size.SHORT;
        price();
    }

    /**
     * Copy constructor for a Coffee.
     * This constructor is used to pass a coffee object from a view, to an order.
     * @param copy The coffee object to be cloned.
     */
    public Coffee(Coffee copy) {
        super(copy);
        this.size = copy.size;
        this.addIns = copy.addIns;
        this.quantity = copy.quantity;
        setImage(copy.getImage());
    }

    /**
     * Returns a copy of the coffee object.
     * @return a copy of the coffee object.
     */
    @Override
    public MenuItem clone() {
        return new Coffee(this);
    }

    /**
     * Abstract method of Menu Item. Determines the price of a Coffee object.
     * @return the price of a Coffee object.
     */
    @Override
    public double price () {
        price = size.getPrice();
        for (AddIns value : addIns) {
            price += value.getPrice();
        }
        price *= quantity;
        setPrice(price);
        return price;
    }

    /**
     * Sets the Size of a coffee object.
     * @param size Size Enum to be set to.
     */
    public void setSize (Size size) {
        this.size = size;
        price();
    }

    /**
     * Adds a AddIn enum to the coffee's add-in list.
     * @param newAddIn AddIns enum to be added.
     * @return true if successful, false otherwise.
     */
    public boolean addAddIn(AddIns newAddIn) {
        if (!addIns.contains(newAddIn)) {
            addIns.add(newAddIn);
            price();
            return true;
        }
        return false;
    }

    /**
     * Removes a AddIns enum from the coffee's add-in list.
     * @param removeAddIn AddIns enum to be removed.
     * @return true if successful, false otherwise.
     */
    public boolean removeAddIn(AddIns removeAddIn) {
        boolean success = addIns.remove(removeAddIn);
        price();
        return success;
    }

    /**
     * Sets the quantity of the coffee object.
     * @param amount quantity amount to be set to.
     */
    public void setQuantity(int amount){
        quantity = amount;
        price();
    }



    /**
     * Override and formats a coffee's object toString method.
     * @return String detailing the quantity, size, and add-ins of a coffee.
     */
    @Override
    public String toString () {
        String addinString = addIns.isEmpty() ? "None" : addIns.toString();
        return quantity + " " + size + " Coffee Add-Ins: " + addinString;
    }

}
