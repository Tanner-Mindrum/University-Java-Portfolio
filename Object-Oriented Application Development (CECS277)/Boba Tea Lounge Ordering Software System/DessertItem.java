/**
 * The DessertItem superclass contains general methods and capabilities to find characteristics of a dessert item. Also
 * has ability to compare two DessertItem objects by implementing the comparable interface.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public abstract class DessertItem implements Comparable {

    //Instance variables
    private String dessertName;
    private int quantity;
    private int price;
    private static double max;
    private static double maxCount;
    private boolean couponApplied;
    private double cost;


    /**
     * Default constructor initializes instance variables
     */
    public DessertItem() {
        dessertName = "";
        quantity = 0;
        max = 0;
        maxCount = 0;
        couponApplied = false;
        this.cost = 0;
    }

    /**
     * Gets the name of a dessert
     * @return - the name of a dessert
     */
    public String getDessertName() {
        return dessertName;
    }

    /**
     * Sets the name of a dessert
     * @param dessertName - a string representing the name of a dessert
     */
    public void setDessertName(String dessertName) {
        this.dessertName = dessertName;
    }

    /**
     * Gets a boolean that notifies classes whether a coupon has been used
     * @return - a boolean value representing if a coupon has been used
     */
    public boolean getCouponApplied() {
        return couponApplied;
    }

    /**
     * Sets the boolean value that checks whether a coupon has been used
     * @param couponUsed - a boolean value that represents whether a coupon has been used
     */
    public void setCouponApplied(boolean couponUsed) {
        this.couponApplied = couponUsed;
    }

    /**
     * Gets the price after a discount was applied from a coupon
     * @return - the cost after coupon has been applied
     */
    public double getCouponDiscount() {
        return this.cost;
    }

    /**
     * Sets the price to the new price after a discount was applied
     * @param discountPrice - the price after discount was applied
     */
    public void setCouponDiscount(double discountPrice) {
        this.cost = discountPrice;
    }

    /**
     * Gets the amount of a single dessert item purchased
     * @return - an integer representing the quantity of a dessert item
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the amount of dessert items purchased
     * @param quantity - the amount of a dessert item purchased
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the price of a dessert item
     * @return - an integer representing the price of a dessert item
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of a dessert item
     * @param price - the price of a dessert item
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Default toString
     * @return - a string notifying the user that a specific dessert was not found or purchased
     */
    public String toString() {
        return "Dessert item not found.";
    }

    /**
     * Static max method sets a static variable max to the highest priced dessert
     * @param dessert - a DessertItem object which represents a dessert of highest cost
     */
    public static void max(DessertItem dessert) {
        max = dessert.getCost();
    }

    /**
     * Compares DessertItem objects based on the highest cost
     * @param Dessert - an Object that is a dessert that will be compared
     * @return - an integer representing the outcome of a comparison
     */
    public int compareTo(Object Dessert) {
        DessertItem aDessert = (DessertItem) Dessert;
        if (max > aDessert.getCost()) {
            return -1;
        }
        else if (max < aDessert.getCost()) {
            max(aDessert);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Abstract method that is overridden in subclasses to find cost of drink
     * @return - will generate the cost of a dessert
     */
    public abstract double getCost();
}
