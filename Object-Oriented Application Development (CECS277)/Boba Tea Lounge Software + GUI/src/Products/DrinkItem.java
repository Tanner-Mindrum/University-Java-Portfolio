package Products;

/**
 * The DrinkItem class is a subclass of the Item class. It contains a default and overloaded constructor, as well
 * as setters/getters, a default toString, and an abstract method that is overridden in CoffeeItem and TeaItem, the
 * two subclasses of DrinkItem.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class DrinkItem extends Item {

    protected String size;
    protected String flavor;
    protected String sweetness;
    protected String milk;

    public DrinkItem() {
        this.size = "N/A";
        this.flavor = "N/A";
        this.sweetness = "N/A";
        this.milk = "N/A";
    }

    public DrinkItem(String drinkName, String drinkSize, String drinkFlavor, String drinkSweetness, String drinkMilk) {
        this.name = drinkName;
        this.size = drinkSize;
        this.flavor = drinkFlavor;
        this.sweetness = drinkSweetness;
        this.milk = drinkMilk;
    }

    public void setFlavor(String drinkFlavor) {
        this.flavor = drinkFlavor;
    }

    public void setMilk(String drinkMilk) {
        this.milk = drinkMilk;
    }

    public void setSize(String drinkSize) {
        this.size = drinkSize;
    }

    public void setSweetness(String drinkSweetness) {
        this.sweetness = drinkSweetness;
    }

    public String getFlavor() {
        return this.flavor;
    }

    public String getMilk() {
        return this.milk;
    }

    public String getSize() {
        return this.size;
    }

    public String getSweetness() {
        return this.sweetness;
    }

    public String toString() {
        return "Placeholder string for DrinkItem class";
    }

    public double calculateCost() {
        return 0;
    }
}
