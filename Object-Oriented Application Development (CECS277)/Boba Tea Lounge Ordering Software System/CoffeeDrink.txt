import java.text.DecimalFormat;

/**
 * The CoffeeDrink class is a subclass of the DrinkItem superclass that contains method overrides and has
 * specific implementation for a coffee drink.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public class CoffeeDrink extends DrinkItem {

    //Instance variables
    private int size;
    private double costs[] = {3.75, 4.50, 5.75};
    private double cost;
    private String bases[] = {"Water", "Whole milk", "Almond milk"};
    DecimalFormat df = new DecimalFormat("0.00");

    /**
     * Default constructor initializes instance variables
     */
    public CoffeeDrink() {
        this.size = 0;
        this.cost = 0;
    }

    /**
     * Sets the coffee base by selecting a string from the bases array.
     * @param baseChoice - the user's base of choice
     **/
    public void setBase(String baseChoice) {
        super.setBase(bases[Integer.parseInt(baseChoice) - 1]);
    }

    /**
     * Calculates sweetness by number of sugar teaspoons.
     * @param sweetnessChoice - number of sugar teaspoons.
     **/
    public void setSweetness(String sweetnessChoice) {
        super.setSweetness(sweetnessChoice);
    }

    /**
     * Calculates cost of coffee based on size.
     * @return - cost of the coffee drink
     **/
    public double getCost(){
        if (super.getSize().equals("Small")) {
            size = 1;
        }
        else if (super.getSize().equals("Medium")) {
            size = 2;
        }
        else {
            size = 3;
        }
        this.cost = costs[this.size - 1];
        return this.cost;
    }

    /**
     * Formats the description of a coffee drink purchased in receipt format that lists coffee sold and its details
     * @return - a nicely formatted string in receipt format
     */
    public String toString() {
        if (!super.getCouponApplied()) {
            return String.format("%-40s%s%s%n%5s%s%5s%n%5s%5s", super.getSize() + " " + super.getDrinkName(), "$", df.format(getCost()), "*", super.getSweetness(), " sugars", "*", super.getBase());
        }
        else {
            return String.format("%-40s%s%s%n%5s%s%5s%n%5s%5s%n%5s%s", super.getSize() + " " + super.getDrinkName(), "$", df.format(getCouponDiscount()), "*", super.getSweetness(), " sugars", "*", super.getBase(), "*", "Coupon used");
        }
    }
}
