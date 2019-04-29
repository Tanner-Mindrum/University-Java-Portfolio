import java.text.DecimalFormat;

/**
 * The Pastry class is a subclass of the DessertItem superclass that contains method overrides and has
 * specific implementation for a pastry item.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public class Pastry extends DessertItem {

    //Instance variables
    private String heating;
    private final double COST_TO_HEAT = .25;
    private final double COST_OF_CINNAMON_ROLL = 3.00;
    private final double COST_OF_MUFFIN = 2.50;
    private double cost;
    DecimalFormat df = new DecimalFormat("0.00");


    /**
     * Default constructor initializes instance variables
     */
    public Pastry () {
        heating = "";
        cost = 0.0;
    }

    /**
     * Sets the heating option for a pastry
     * @param heatChoice - the user's choice to heat or not
     */
    public void setHeating (int heatChoice) {
        if (heatChoice == 1) {
            this.heating = "Heated";
        }
        else {
            this.heating = "Not heated";
        }
    }

    /**
     * Gets the heating option of a pastry
     * @return - a string representing the heating option
     */
    public String getHeating() {
        return this.heating;
    }

    /**
     * Calculates the price of a pastry based on the quantity and heating option.
     * @return - the total price of a pastry item
     */
    public double getCost() {
        if (this.heating.equals("Heated")) {
            if (super.getDessertName().equals("Cinnamon roll")) {
                this.cost = (super.getQuantity() * COST_OF_CINNAMON_ROLL) + (COST_TO_HEAT * super.getQuantity());
            }
            else {
                this.cost = (super.getQuantity() * COST_OF_MUFFIN) + (COST_TO_HEAT * super.getQuantity());
            }
        }
        else {
            if (super.getDessertName().equals("Cinnamon roll")) {
                this.cost = super.getQuantity() * COST_OF_CINNAMON_ROLL;
            }
            else {
                this.cost = super.getQuantity() * COST_OF_MUFFIN;
            }
        }
        return this.cost;
    }

    /**
     * Formats the description of a pastry item purchased in receipt format that lists pastry items sold and its details
     * @return - a nicely formatted string in receipt format
     */
    public String toString() {
        if (!super.getCouponApplied()) {
            return String.format("%-40s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCost()), "*", getHeating());
        }
        else {
            return String.format("%-40s%s%n%5s%s%n%5s%s", super.getQuantity() + "x " + super.getDessertName(), "$" + df.format(getCouponDiscount()), "*", getHeating(), "*", "Coupon used");
        }
    }
}
