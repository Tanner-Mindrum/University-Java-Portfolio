import java.text.DecimalFormat;

/**
 * The Macaroon class is a subclass of the DessertItem superclass that contains method overrides and has
 * specific implementation for a macaroon item.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public class Macaroon extends Cookie {

    //Instance variables
    private double cost;
    private int trioQuant;
    private int oneQuant;
    private final double COST_OF_ORANGE_MACAROON = 1.50;
    private final double COST_OF_ORANGE_MACAROON_TRIO = 3.00;
    private final double COST_OF_CHERRY_MACAROON = 1.00;
    private final double COST_OF_CHERRY_MACAROON_TRIO = 2.00;
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Default constructor initializes instance variables
     */
    public Macaroon () {
        this.cost = 0;
        this.trioQuant = 0;
        this.oneQuant = 0;
    }

    /**
     * Calculates the cost of a macaroon item based off the quantity and if they qualify for trio pricing or not
     * @return - the final cost of a macaroon item
     */
    public double getCost() {
        this.oneQuant = super.getQuantity() % 3;
        this.trioQuant = super.getQuantity() / 3;
        if (super.getDessertName().equals("Orange macaroon")) {
            this.cost = (this.trioQuant * COST_OF_ORANGE_MACAROON_TRIO) + (this.oneQuant * COST_OF_ORANGE_MACAROON);
        }
        else {
            this.cost = (this.trioQuant * COST_OF_CHERRY_MACAROON_TRIO) + (this.oneQuant * COST_OF_CHERRY_MACAROON);
        }
        return this.cost;
    }

    /**
     * Formats the description of a macaroon item purchased in receipt format that lists macaroon items sold and its details
     * @return - a nicely formatted string in receipt format
     */
    public String toString() {
        if (!super.getCouponApplied()) {
            if (this.trioQuant != 0) {
                return String.format("%-40s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCost()),
                        "*", this.trioQuant + " sets of " + getDessertName() + "s @ trio price");
            }
            else {
                return String.format("%-40s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCost()));
            }
        }
        else {
            if (this.trioQuant != 0) {
                return String.format("%-40s%s%n%5s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCouponDiscount()),
                        "*", this.trioQuant + " sets of " + getDessertName() + "s @ trio price", "*", "Coupon used");
            }
            else {
                return String.format("%-40s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCouponDiscount()), "*", "Coupon used");
            }
        }
    }
}
