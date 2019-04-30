package Products;

/**
 * The abstract Item class is the main superclass in this program's inheritance hierarchy. It contains methods that
 * are shared and may be overridden in the CoffeeItem and TeaItem classes, which are Item's two subclasses.
 * Contains constructor, overloaded constructor, setters/getters, and abstract method, calculateCost, used to find
 * cost of an order.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public abstract class Item {

    protected String name;
    protected double cost;

    public Item() {
        this.name = "N/A";
        this.cost = 0.0;
    }

    public Item(String itemName, double itemCost) {
        this.name = itemName;
        this.cost = itemCost;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    public String getName() {
        return this.name;
    }

    public void setCost(double itemCost) {
        this.cost = itemCost;
    }

    public double getCost() {
        return this.cost;
    }

    public abstract double calculateCost();
}
