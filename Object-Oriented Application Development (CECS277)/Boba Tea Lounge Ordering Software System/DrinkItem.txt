/**
 * The DrinkItem superclass contains general methods and capabilities to find characteristics of a drink item. Also
 * has ability to compare two DrinkItem objects by implementing the comparable interface.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public abstract class DrinkItem implements Comparable {

    //Instance variables
    private String drinkName;
    private String base;
    private String sweetness;
    private String size;
    private double cost;
    private String sizes[] = {"Small", "Medium", "Large"};
    private static double max;
    private static double maxCount;
    private boolean couponApplied;

    /**
     * Default constructor initializes instance variables
     */
    public DrinkItem(){
        base = "";
        sweetness = "";
        size = "";
        max = 0;
        maxCount = 0;
        couponApplied = false;
    }

    /**
     * Gets the name of a drink
     * @return - the name of a drink
     */
    public String getDrinkName() {
        return this.drinkName;
    }

    /**
     * Sets the name of a drink
     * @param drink - a string representing the name of a drink
     */
    public void setDrinkName(String drink) {
        this.drinkName = drink;

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
     * Gets the name of the drink's base
     * @return - a string representing a drink's base
     */
    public String getBase() {
        return base;
    }

    /**
     * Sets the name of the drink's base
     * @param theirBase - a string name of the base
     */
    public void setBase(String theirBase) {
        this.base = theirBase;
    }

    /**
     * Gets the sweetness of a drink
     * @return - a string representing the sweetness of a drink
     */
    public String getSweetness() {
        return sweetness;
    }

    /**
     * Sets the sweetness of a drink
     * @param sweetness - a string description of a drink's sweetness
     */
    public void setSweetness(String sweetness) {
        this.sweetness = sweetness;
    }

    /**
     * Gets the size of a drink
     * @return - a string representing the size of a drink
     */
    public String getSize() {
        return size;
    }

    /**
     * Sets the size of a drink, chooses the size from an array of sizes
     * @param size - the user's size of a choice
     */
    public void setSize(String size) {
        this.size = sizes[Integer.parseInt(size) - 1];
    }

    /**
     * Default toString
     * @return - a string notifying the user that a specific drink was not found or purchased
     */
    public String toString() {
        return "Drink item not found.";
    }

    /**
     * Validates specific decisions that have three options, such as size
     * @param option - an integer option chosen by user from list of menu or sub-menu options
     * @return - a boolean representing if an option is valid or not
     */
    public boolean validateOption(int option) {
        if (option < 1 || option > 3) {
            System.out.println("That option does not exist, try again.\n");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * Static max method sets a static variable max to the highest priced drink
     * @param drink - a DrinkItem object which represents a drink of highest cost
     */
    public static void max(DrinkItem drink) {
        max = drink.getCost();
    }

    /**
     * Compares DrinkItem objects based on the highest cost
     * @param Drink - an Object that is a drink that will be compared
     * @return - an integer representing the outcome of a comparison
     */
    public int compareTo(Object Drink) {
        DrinkItem aDrink = (DrinkItem) Drink;
        if (max > aDrink.getCost()) {
            return -1;
        }
        else if (max < aDrink.getCost()) {
            max(aDrink);
            return 1;
        }
        else {
            return 0;
        }
    }

    /**
     * Abstract method that is overridden in subclasses to find cost of drink
     * @return - will return the cost of a drink
     */
    public abstract double getCost();
}
