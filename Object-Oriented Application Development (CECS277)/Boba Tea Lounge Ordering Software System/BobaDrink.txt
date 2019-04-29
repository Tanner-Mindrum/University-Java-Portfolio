import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The BobaDrink class is a subclass of the DrinkItem superclass that contains method overrides and has
 * specific implementation for a boba drink.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public class BobaDrink extends DrinkItem {

    //Instance variables
    private int cost;
    private String sweetness;
    private int size;
    private String milk;
    private final double PRICE_PER_TOPPING = .25;
    private double costs[] = {3.75, 4.50, 5.75};
    private String[] bases = {"Green tea", "Black tea", "Jasmine green tea", "Rose tea", "Oolong tea"};
    private String[] toppings = {"Boba", "Popping boba", "Grass jelly", "Lychee jelly", "Coconut jelly", "Mini mochi"};
    private String[] milks = {"Whole milk", "Half-and-half", "Almond milk", "Coconut milk", "No milk"};
    private String[] sweetnesses = {"Unsweetened", "1/4 sweet", "1/2 sweet", "3/4 sweet", "Full"};
    private ArrayList<String> chosenToppings;
    DecimalFormat df = new DecimalFormat("0.00");


    /**
     * Default constructor initializes instance variables
     */
    public BobaDrink () {
        sweetness = "";
        size = 0;
        chosenToppings = new ArrayList<String>();
    }

    /**
     * Sets the sweetness of a boba drink, chooses from array of sweetness options
     * @param sweetnessChoice - the user's choice of sweetness
     */
    public void setSweetness(String sweetnessChoice) {
        super.setSweetness(sweetnesses[Integer.parseInt(sweetnessChoice) - 1]);
    }

    /**
     * Adds toppings of a boba drink into an ArrayList of string toppings chosen from toppings array
     * @param toppingChoice - the user's topping of choice
     */
    public void addToppings(int toppingChoice) {
        chosenToppings.add(toppings[toppingChoice - 2]);
    }

    /**
     * Gets the toppings that the user selected
     * @return - a string detailing the toppings that the user selected
     */
    public String getToppings() {
        String toppings = "";
        if (!(chosenToppings.size() == 0)) {
            for (int i = 0; i < chosenToppings.size(); i++) {
                if (!(i == chosenToppings.size() - 1)) {
                    toppings += chosenToppings.get(i) + ", ";
                } else {
                    toppings += chosenToppings.get(i);
                }
            }
        }
        else {
            toppings = "No toppings were added.";
        }
        return toppings;
    }

    /**
     * Sets the milk of a boba drink, chooses from milk array
     * @param milkChoice - the user's choice of milk
     */
    public void setMilk(int milkChoice) {
        this.milk = milks[milkChoice - 1];
    }

    /**
     * Gets the milk of a boba drink
     * @return - the boba drink's milk base
     */
    public String getMilk() {
        return milk;
    }

    /**
     * Sets the base of a boba drink
     * @param baseChoice - the user's base of choice
     */
    public void setBases(String baseChoice) {
        super.setBase(bases[Integer.parseInt(baseChoice) - 1]);
    }

    /**
     * Calculates the cost of a boba drink based on size and number of topppings
     * @return - the final cost of that boba drink
     */
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
        return costs[size - 1] + (chosenToppings.size() * PRICE_PER_TOPPING);
    }

    /**
     * Formats the description of a boba drink purchased in receipt format that lists boba drinks sold and its details
     * @return - a nicely formatted string in receipt format
     */
    public String toString() {
        if (!super.getCouponApplied()) {
            if (!(getToppings().equals("No toppings were added."))) {
                return String.format("%-40s%s%s%n%5s%5s%n%5s%5s%n%5s%s", super.getSize() + " " + super.getBase(), "$",
                        df.format(getCost()), "*", super.getSweetness(), "*", getMilk(), "*", getToppings());
            } else {
                return String.format("%-40s%s%s%n%5s%5s%n%5s%5s", super.getSize() + " " + super.getBase(), "$",
                        df.format(getCost()), "*", super.getSweetness(), "*", getMilk());
            }
        }
        else {
            if (!(getToppings().equals("No toppings were added."))) {
                return String.format("%-40s%s%s%n%5s%5s%n%5s%5s%n%5s%s%n%5s%s", super.getSize() + " " + super.getBase(), "$",
                        df.format(getCouponDiscount()), "*", super.getSweetness(), "*", getMilk(), "*", getToppings(), "*", "Coupon used");
            } else {
                return String.format("%-40s%s%s%n%5s%5s%n%5s%5s%n%5s%s", super.getSize() + " " + super.getBase(), "$",
                        df.format(getCouponDiscount()), "*", super.getSweetness(), "*", getMilk(), "*", "Coupon used");
            }
        }
    }
}
