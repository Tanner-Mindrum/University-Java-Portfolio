import java.text.DecimalFormat;

/**
 * The Cookie class is a subclass of the DessertItem superclass that contains method overrides and has
 * specific implementation for a cookie item.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public class Cookie extends DessertItem {

    //Instance variables
    private double cost;
    private int oneQuant;
    private int dozenQuant;
    private final double COST_OF_OATMEAL_RAISIN = 2.00;
    private final double COST_OF_OATMEAL_RAISIN_PER_DOZEN = 12.00;
    private final double COST_OF_WHITE_CHOCOLATE_CHUNK = 2.50;
    private final double COST_OF_WHITE_CHOCOLATE_CHUNK_PER_DOZEN = 15.00;
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Default constructor initializes instance variables
     */
    public Cookie () {
        this.cost = 0;
        this.oneQuant = 0;
        this.dozenQuant = 0;
    }

    /**
     * Calculates the cost of cookies based on the quantity and if they qualify for dozen pricing or not
     * @return - the final cost of a cookie item
     */
    public double getCost() {
        this.oneQuant = super.getQuantity() % 12;
        this.dozenQuant = super.getQuantity() / 12;
        if (super.getDessertName().equals("Oatmeal raisin")) {
            this.cost = (this.oneQuant * COST_OF_OATMEAL_RAISIN) + (this.dozenQuant * COST_OF_OATMEAL_RAISIN_PER_DOZEN);
        }
        else {
            this.cost = (this.oneQuant * COST_OF_WHITE_CHOCOLATE_CHUNK) + (this.dozenQuant * COST_OF_WHITE_CHOCOLATE_CHUNK_PER_DOZEN);
        }
        return this.cost;
    }

    /**
     * Formats the description of a cookie item purchased in receipt format that lists cookie items sold and its details
     * @return - a nicely formatted string in receipt format
     */
    public String toString() {
        if (!super.getCouponApplied()) {
            if (this.dozenQuant == 0) {
                return String.format("%-40s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCost()));
            }
            else {
                return String.format("%-40s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCost()),
                        "*", this.dozenQuant + " sets of " + getDessertName() + "s @ dozen price");
            }
        }
        else {
            if (this.dozenQuant == 0) {
                return String.format("%-40s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCouponDiscount()), "*", "Coupon used");
            }
            else {
                return String.format("%-40s%s%n%5s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCouponDiscount()),
                        "*", this.dozenQuant + " sets of " + getDessertName() + "s @ dozen price", "*", "Coupon used");
            }
        }
    }
}
