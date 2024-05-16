package entities;

import java.util.ArrayList;

/**
 * Class meant to represent a sandwich for a cafe software, extends MenuItem.
 * Sandwiches can be either Beef ($10.99), Chicken ($8.99) or Fish ($9.99) with a selection of three kinds of
 * bread: Sourdough, Bagel, Wheat. Customer can add-on Lettuce, Tomatoes, Onions and/Cheese for an extra charge.
 * ($0.30 for veggies and $1.00 for cheese)
 *
 * @author Ashley Berlinski
 */
public class Sandwich extends MenuItem {

    /**
     * Enum meant to represent the proteins of sandwiches a person can order along with their prices.
     */
    public enum Protein{
        BEEF(10.99), CHICKEN(8.99), FISH(9.99);

        final double price;

        /**
         * Constructor for Protein, takes in price as parameter
         * @param price the price of the sandwich.
         */
        Protein(double price) {
            this.price = price;
        }

        /**
         * Returns price of sandwich
         * @return price of sandwich.
         */
        public double getPrice() {
            return this.price;
        }

        /**
         * Returns a string representing the type of protein and its price.
         * @return a string with all the information about a protein.
         */
        @Override
        public String toString() {
            return this.name() + " - $" + price;
        }
    }

    /**
     * Enum meant to represent add-ins for a sandwich along with their extra charge/price.
     */
    public enum AddOn {
        CHEESE(1.0), LETTUCE(0.3), TOMATOES(0.3), ONIONS(0.3);

        final double price;

        /**
         * Constructor for AddIn, takes in price as parameter
         * @param price the price of the AddIn.
         */
        AddOn(double price) {
            this.price = price;
        }

        /**
         * Returns price of addIn
         * @return price of addIn.
         */
        public double getPrice() {
            return this.price;
        }
    }

    /**
     * Enum meant to represent the types of bread for a sandwich.
     */
    public enum Bread {
        BAGEL, WHEAT, SOURDOUGH;
    }

    private Protein protein;
    private Bread bread;
    private ArrayList<AddOn> addOns;

    /**
     * Constructor for a sandwich, takes in the protein and its bread.
     * @param protein the protein of sandwich
     * @param bread the bread of the sandwich
     */
    public Sandwich(Protein protein, Bread bread) {
        this.protein = protein;
        this.bread = bread;
        this.addOns = new ArrayList<>();
        setPrice(price());
    }

    /**
     * Clone constructor for a sandwich, takes in a sandwich object and makes an identical one.
     * @param sandwich the sandwich to be copied.
     */
    public Sandwich(Sandwich sandwich) {
        super(sandwich);
        this.protein = sandwich.protein;
        this.bread = sandwich.bread;
        this.addOns = new ArrayList<>();
        this.addOns.addAll(sandwich.addOns);
        setImage(sandwich.getImage());
    }

    /**
     * Default constructor for sandwich, all values are null.
     */
    public Sandwich() {
        this.addOns = new ArrayList<>();
        setPrice(0);
    }

    /**
     * Helper method, returns the extra charge for all the add-ons in the sandwich
     * @return the price of all the add-ons in the sandwich
     */
    private double getAddOnsPrice() {
        double price = 0;
        for (AddOn extraAddOns : addOns) {
            price += extraAddOns.getPrice();
        }
        return price;
    }

    /**
     * Helper method, returns a string containing all the adds-ons in the sandwich
     * @return returns a string containing all the adds-ins in the sandwich, if there is none returns "NONE"
     */
    private String addOnsString() {
        String addOnString = "";
        int removeCommaAndSpace = 2;

        for (AddOn extraAddOns : addOns) {
            addOnString += extraAddOns.name().toLowerCase() + ", ";
        }

        if(addOnString.isEmpty())
            return "none";
        else
            return addOnString.substring(0, addOnString.length() - removeCommaAndSpace);
    }

    /**
     * Given a protein, sets the type of the protein to that value.
     * @param protein the type of protein the sandwich will be changed to.
     */
    public void setProtein(Protein protein) {
        this.protein = protein;
        setPrice(price());
    }

    /**
     * Given a bread, sets the bread of the sandwich to that value.
     * @param bread the type of bread the sandwich will be changed to.
     */
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    /**
     * Given an add-On adds that to the sandwich if it wasn't already on there.
     * @param addOn the add-on attempting to be added.
     * @return true if was successful in adding, false if otherwise.
     */
    public boolean addAddOn(AddOn addOn) {
        if(!(addOns.contains(addOn))) {
            addOns.add(addOn);
            setPrice(price());
            return true;
        }
        return false;
    }

    /**
     * Given an array of add-Ons adds them to the sandwich if they weren't already on there.
     * @param addOns the add-ons attempting to be added.
     */
    public void addAddOns(AddOn [] addOns) {
        for (AddOn extraAddOns : addOns)
            addAddOn(extraAddOns);
    }

    /**
     * Given an add-In removes that off the sandwich if it was there.
     * @param addOn the add-in attempting to be removed.
     * @return true if was successful in removing, false if otherwise.
     */
    public boolean removeAddOn(AddOn addOn) {
        boolean success = addOns.remove(addOn);
        setPrice(price());
        return success;
    }

    /**
     * Given an array of add-ons removes them from the sandwich if they were already on there.
     * @param addOns the add-ons attempting to be removed.
     */
    public void removeAddOns(AddOn [] addOns) {
        for (AddOn extraAddOns : addOns)
            removeAddOn(extraAddOns);
    }

    /**
     * Determines if a sandwich does not have its protein or bread set.
     * @return true if either protein or bread is null, false otherwise.
     */
    public boolean isIncomplete() {
        return protein == null || bread == null;
    }

    /**
     * Returns a copy of the sandwich object.
     * @return a copy of the sandwich object.
     */
    @Override
    public MenuItem clone() {
        return new Sandwich(this);
    }

    /**
     * Returns the price of the sandwich based on the protein and the extra add-ons
     * @return the price of the sandwich.
     */
    @Override
    public double price() {
        double price = 0;
        int rounding = 100;
        double proteinPrice = protein != null ? (protein.getPrice() * rounding) : 0;
        price += proteinPrice;
        price += (getAddOnsPrice() * rounding);
        return price / rounding;
    }


    /**
     * Returns a string representing the sandwich, lists its protein, its bread, its add-ons and its price.
     * @return a string of the format "Sandwich: [protein] \tBread: [bread] \t Add-Ons: [add-Ons] \t Price: [price]"
     */
    @Override
    public String toString() {
        return protein.name().toLowerCase() + " sandwich with " + bread.toString().toLowerCase() +
                " bread and add-ons: (" + addOnsString() + ")";
    }
}
