package entities;

/**
 * Class meant to represent donuts for a cafe software, extends MenuItem.
 * Donuts can be either Yeast ($1.79), Cake ($1.89) or Holes ($0.39).
 * Yeast donuts come in the flavors: Jelly, Apple Cider, Boston Cream, Chocolate, Powdered, Glazed
 * Cake donuts come in the flavors: Vanilla, Pink Frosted, Funfetti
 * Donut Holes comes in the flavors: Chocolate, Powdered, Glazed
 * There is also a quantity of donuts.
 *
 * @author Ashley Berlinski
 */
public class Donut extends MenuItem {

    /**
     * Enum meant to represent the flavor of donut offered.
     */
    public enum Flavor {
        JELLY("JELLY"), APPLE_CIDER("APPLE CIDER"), BOSTON_CREAM("BOSTON CREAM"), //Yeast
        CHOCOLATE("CHOCOLATE"), POWDERED("POWDERED"), GLAZED("GLAZED"), //Yeast & Holes
        VANILLA("VANILLA"), PINK_FROSTED("PINK FROSTED"), FUNFETTI("FUNFETTI"); //Cake

        final String name;

        /**
         * Constructor for flavor, takes in name of flavor as parameter
         * @param name the name of the flavor.
         */
        Flavor(String name){
            this.name = name;
        }

        /**
         * Returns a string representing the flavor of donut.
         * @return a string with the flavor of donut.
         */
        @Override
        public String toString() {
            return this.name;
        }

    }

    /**
     * Enum meant to represent the type of donut a person can order along with their prices.
     */
    public enum Type {
        YEAST(1.79), CAKE(1.89), HOLE(0.39);
        final double price;

        /**
         * Constructor for donut type, takes in price as parameter
         * @param price the price of the type of donut.
         */
        Type(double price) {
            this.price = price;
        }

        /**
         * Returns price of the type of donut
         * @return price of type of donut.
         */
        public double getPrice() {
            return this.price;
        }

        /**
         * Returns an array of flavors that the corresponding donut type can come in.
         * @return an array of flavors that the corresponding donut type can come in.
         */
        public Flavor[] getFlavors() {
            switch(this) {
                case YEAST:
                    return new Flavor[]{Flavor.CHOCOLATE, Flavor.POWDERED, Flavor.GLAZED,
                        Flavor.JELLY, Flavor.APPLE_CIDER, Flavor.BOSTON_CREAM};
                case HOLE:
                    return new Flavor[]{Flavor.CHOCOLATE, Flavor.POWDERED, Flavor.GLAZED};
                case CAKE:
                    return new Flavor[]{Flavor.VANILLA, Flavor.PINK_FROSTED, Flavor.FUNFETTI};
                default:
                    return null;
            }
        }

        /**
         * Returns a string representing the type of donut and its price.
         * @return a string with all the information about a donut.
         */
        @Override
        public String toString() {
            return this.name() + " - $" + price;
        }
    };

    private Type type;
    private Flavor flavor;
    private int quantity;
    private int image;


    /**
     * Constructor for a donut, takes in a type of donut and the quantity of them.
     * @param type the type of donut
     * @param flavor the flavor of the donut
     * @param quantity the quantity of donuts.
     * @param image the resource id associated with the donut flavor
     */
    public Donut (Type type, Flavor flavor, int quantity, int image) {
        this.type = type;
        this.flavor = flavor;
        this.quantity = quantity;
        this.image = image;
        setPrice(price());
    }

    /**
     * Clone constructor for a donut, takes in a donut object and makes an identical one.
     * @param donut the donut to be copied.
     */
    public Donut(Donut donut){
        this.flavor = donut.flavor;
        this.type = donut.type;
        this.quantity = donut.quantity;
        this.image = donut.image;
        setPrice(price());
    }

    /**
     * Given a new donut type, changes the donut to that type.
     * @param type the type the donut will be changed to.
     */
    public void setType(Type type) {
        this.type = type;
        setPrice(price());
    }

    /**
     * Given a flavor, sets the flavor of the donut to that.
     * @param flavor the new flavor of the donut.
     */
    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    /**
     * Given a quantity, sets the quantity of the donut to that value.
     * @param quantity the new quantity of the donut.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setPrice(price());
    }

    /**
     * Returns the quantity of the donut
     * @return the quantity of the donut.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns specific image id associated with the donut object.
     * @return specific image id associated with the donut object.
     */
    public int getImage() {
        return this.image;
    }

    /**
     * Returns a string representing all attributes of a donut except for its price.
     * @return a string representing all attributes of a donut except for its price, returns incomplete donut
     * if donut has null attributes.
     */
    public String getDonutText() {
        if(!isIncomplete()) {
            String donut = (type == Type.HOLE) ? "donut " + type.name().toLowerCase() : type.name().toLowerCase()
                    + " donut";
            return flavor.name.toLowerCase() + " " + donut;
        }
        return "incomplete donut";
    }

    /**
     * Determines if a donut does not have its flavor set.
     * @return true if flavor is null, false otherwise.
     */
    public boolean isIncomplete() {
        return flavor == null;
    }

    /**
     * Calculates the price of the donuts depending on their attributes.
     * @return the price of the donuts.
     */
    @Override
    public double price() {
        int rounding = 100;
        double price = type.getPrice() * rounding;
        return (price * quantity) / rounding;
    }

    /**
     * Returns a copy of the donut object.
     * @return a copy of the donut object.
     */
    @Override
    public MenuItem clone() {
        return new Donut(this);
    }

    /**
     * Returns a string representing all attributes of a donut.
     * @return a string representing all attributes of a donut, returns incomplete donut if donut has null attributes.
     */
    @Override
    public String toString() {
        if(!isIncomplete()) {
            return getDonutText() + " - quantity: (" + quantity + ")";
        }
        return "incomplete donut";
    }

}