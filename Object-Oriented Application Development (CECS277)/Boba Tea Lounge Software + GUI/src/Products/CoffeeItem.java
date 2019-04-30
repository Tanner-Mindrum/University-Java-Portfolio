package Products;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The CoffeeItem class defines the characteristics of any coffee item that can be purchased using this software.
 * Calculates the cost based on the drink's size, temperature, and if they add milk. Contains a toString that formats
 * the string nicely in a modern receipt format.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class CoffeeItem extends DrinkItem {

    protected String temperature;
    protected String specialInstructions;

    public CoffeeItem() {
        temperature = "N/A";
        specialInstructions = "N/A";
    }

    public CoffeeItem(String drinkName, String drinkSize, String drinkFlavor, String drinkSweetness, String drinkMilk, String coffeeTemperature, String coffeeSpecialInstructions) {
        this.name = drinkName;
        this.size = drinkSize;
        this.flavor = drinkFlavor;
        this.sweetness = drinkSweetness;
        this.milk = drinkMilk;
        this.temperature = coffeeTemperature;
        this.specialInstructions = coffeeSpecialInstructions;
    }

    public double calculateCost() {
        ArrayList<Double> sizePrice = new ArrayList<>();
        sizePrice.add(1.00);
        sizePrice.add(1.50);
        sizePrice.add(2.00);

        if (this.temperature.equals("blended")) {
            for (int i = 0; i < sizePrice.size(); i++) {
                sizePrice.set(i, sizePrice.get(i) + .25);
            }
        }

        if (!this.milk.equals("no milk")) {
            for (int i = 0; i < sizePrice.size(); i++) {
                sizePrice.set(i, sizePrice.get(i) + .25);
            }
        }

        if (this.size.equals("S")) {
            this.cost = sizePrice.get(0);
        }
        else if (this.size.equals("M")) {
            this.cost = sizePrice.get(1);
        }
        else {
            this.cost = sizePrice.get(2);
        }
        return this.cost;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        if (!this.specialInstructions.equals("")) {
            return String.format("%37s%-25s", " ", "-- " + super.getFlavor() + " " + super.getName() +
                    " (" + super.getSize() + "):") + "\t\t                 $" + df.format(super.getCost()) +
                    String.format("%n%46s%s%n%46s%s%n%46s%s%n%46s%s", " ", "Sweetness: " + super.getSweetness(), " ",
                            "Milk: " + super.getMilk(), " ", "Temp: " + this.temperature, " ", "Special instructions: " +
                                    this.specialInstructions);
        }
        else {
            return String.format("%37s%-25s", " ", "-- " + super.getFlavor() + " " + super.getName() +
                    " (" + super.getSize() + "):") + "\t\t                  $" + df.format(super.getCost()) +
                    String.format("%n%46s%s%n%46s%s%n%46s%s", " ", "Sweetness: " + super.getSweetness(), " ",
                            "Milk: " + super.getMilk(), " ", "Temp: " + this.temperature);
        }
    }
}