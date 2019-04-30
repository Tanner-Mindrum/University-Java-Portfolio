package Products;

import java.text.DecimalFormat;

/**
 * The PastryItem class defines the characteristics of any pastry item that can be purchased using this software.
 * Calculates the cost based on the type of pastry and if the user decides they want to heat their pastry.
 * Contains a toString that formats the string nicely in a modern receipt format.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class PastryItem extends Item {

    protected String flavor;
    protected boolean isHeated;
    public static final double HEAT_PRICE = 0.25;

    public PastryItem(String pastryName, String pastryFlavor, boolean isPastryHeated) {
        this.flavor = pastryFlavor;
        this.name = pastryName;
        this.isHeated = isPastryHeated;
    }

    public double calculateCost() {
        if (this.name.equals("Muffin")) {
            this.cost = 2.00;
        }
        else if (this.name.equals("Cookie")) {
            this.cost = 1.50;
        }
        else if (this.name.equals("Cheesecake slice")) {
            if (this.flavor.equals("Regular")) {
                this.cost = 4.00;
            }
            else if (this.flavor.equals("Cherry")) {
                this.cost = 4.50;
            }
            else {
                this.cost = 4.50;
            }
        }
        else if (this.name.equals("Danish")) {
            this.cost = 2.50;
        }

        if (this.isHeated) {
            this.cost += HEAT_PRICE;
        }
        return this.cost;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        if (this.isHeated) {
            if (!this.name.equals("Cheesecake slice")) {
                return String.format("%37s%-25s", " ", "-- " + this.name +
                        " (" + "Heated" + "):") + "\t\t                  $" + df.format(super.getCost()) +
                        String.format("%n%46s%s", " ", this.flavor);
            }
            else {
                return String.format("%37s%-25s", " ", "-- " + this.name +
                        " (" + "Heated" + "):") + "\t                  $" + df.format(super.getCost()) +
                        String.format("%n%46s%s", " ", this.flavor);
            }
        }
        else {
            if (!this.name.equals("Cheesecake slice")) {
                return String.format("%37s%-25s", " ", "-- " + super.getName()) +
                        "\t\t                  $" + df.format(super.getCost()) + String.format("%n%46s%s", " ", this.flavor);
            }
            else {
                return String.format("%37s%-25s", " ", "-- " + super.getName()) +
                        "\t\t                  $" + df.format(super.getCost()) + String.format("%n%46s%s", " ", this.flavor);
            }
        }
    }
}