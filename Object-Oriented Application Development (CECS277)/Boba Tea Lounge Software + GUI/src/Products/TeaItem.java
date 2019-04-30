package Products;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The TeaItem class defines the characteristics of any tea item that can be purchased using this software.
 * Calculates the cost based on the drink's size, number of toppings, and if they add milk. Contains a toString that
 * formats the string nicely in a modern receipt format.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class TeaItem extends DrinkItem {
    private ArrayList<String> toppings;
    private static final double TOPPING_PRICE = .25;

    public TeaItem() {
        this.size = "N/A";
        this.flavor = "N/A";
        this.sweetness = "N/A";
        this.milk = "N/A";
        toppings = new ArrayList<String>();
    }

    public TeaItem(String drinkSize, String drinkFlavor, String drinkSweetness, String drinkMilk) {
        this.size = drinkSize;
        this.flavor = drinkFlavor;
        this.sweetness = drinkSweetness;
        this.milk = drinkMilk;
        toppings = new ArrayList<String>();
    }

    public void addTopping(String teaTopping) {
        toppings.add(teaTopping);
    }

    public double calculateCost() {
        ArrayList<Double> sizePrice = new ArrayList<>();
        sizePrice.add(2.50);
        sizePrice.add(3.00);
        sizePrice.add(3.50);

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

        for (int i = 0; i < toppings.size(); i++) {
            this.cost += TOPPING_PRICE;
        }
        return this.cost;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        String toppingsList = "";
        for (int i = 0; i < toppings.size(); i++) {
            if (!(i == toppings.size() - 1)) {
                toppingsList += toppings.get(i) + ", ";
            } else {
                toppingsList += toppings.get(i);
            }
        }
        if (toppings.size() != 0) {
            return String.format("%37s%-25s", " ", "-- " + super.getFlavor() +
                    " (" + super.getSize() + "):") + "\t\t                  $" + df.format(super.getCost()) +
                    String.format("%n%46s%s%n%46s%s%n%46s%s", " ", "Sweetness: " +
                            super.getSweetness(), " ", "Milk: " + super.getMilk(), " ", "Toppings: " + toppingsList);
        }
        else {
            return String.format("%37s%-25s", " ", "-- " + super.getFlavor() +
                    " (" + super.getSize() + "):") + "\t\t                  $" + df.format(super.getCost()) +
                    String.format("%n%46s%s%n%46s%s", " ", "Sweetness: " +
                            super.getSweetness(), " ", "Milk: " + super.getMilk());
        }
    }
}