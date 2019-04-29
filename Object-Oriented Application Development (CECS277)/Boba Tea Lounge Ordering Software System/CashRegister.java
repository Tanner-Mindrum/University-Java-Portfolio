package project2;

import project2.DrinkItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * The project2.CashRegister class contains the necessary methods to calculate prices, work with coupons, or find specific
 * details about a sale. Also has the ability to format sales into receipts.
 * @author Tanner Mindrum
 * @since 2019-02-20
 */
public class CashRegister {

    //Instance variables
    private ArrayList<DrinkItem> drinkItems;
    private ArrayList<DessertItem> dessertItems;
    private ArrayList<Double> drinkSubtotals;
    private ArrayList<Double> dessertSubtotals;
    private DrinkItem maxItem;
    private DessertItem maxItemDessert;
    private ArrayList<String> allSales;
    private double drinkSubtotal;
    private double dessertSubtotal;
    private int drinkCount = 0;
    private int dessertCount = 0;
    private String receipt;
    private double maxCost = 0;
    private boolean couponApplied;
    private double totalOfAllSales;
    DecimalFormat df = new DecimalFormat("0.00");


    /**
     * Default constructor initializes instance variables
     */
    public CashRegister () {
        drinkItems = new ArrayList<>();
        dessertItems = new ArrayList<>();
        drinkSubtotals = new ArrayList<>();
        dessertSubtotals = new ArrayList<>();
        allSales = new ArrayList<>();
        drinkSubtotal = 0.0;
        dessertSubtotal = 0.0;
        drinkCount = 0;
        dessertCount = 0;
        receipt = "";
        couponApplied = false;
        maxItem = null;
        maxItemDessert = null;
        totalOfAllSales = 0;
    }

    /**
     * Adds a project2.DrinkItem object into the ArrayList of project2.DrinkItem objects, drinkItems
     * @param drink - a project2.DrinkItem object
     */
    public void addDrink(DrinkItem drink) {
        drinkItems.add(drink);
    }

    /**
     * Adds a project2.DessertItem object into the ArrayList of project2.DessertItem objects, dessertItems
     * @param dessert - a project2.DessertItem object
     */
    public void addDessert(DessertItem dessert) {
        dessertItems.add(dessert);
    }

    /**
     * This method is used to print out all the receipts made while the program is running. Adds receipts into an
     * ArrayList of receipt strings.
     * @param sale - A string representing a receipt
     */
    public void addToAllSales(String sale) {
        allSales.add(sale);
    }

    /**
     * Finds the amount of sales made while the program is running by returning the size of AllSales, an ArrayList
     * of strings representing receipts
     * @return - the size of the AllSales ArrayList which represents total sales made
     */
    public int getAllSalesSize() {
        return allSales.size();
    }

    /**
     * Displays all receipts, each receipt has a "Sale #" header
     */
    public void displayAllsales() {
        int saleCount = 0;
        this.receipt = "";
        for (int i = 0; i < allSales.size(); i++) {
            saleCount++;
            System.out.println(this.receipt += "Sale #" + saleCount + " (Drinks subtotal: $" + df.format(drinkSubtotals.get(i)) + " | " + "Desserts subtotal: $" + df.format(dessertSubtotals.get(i)) + ")" + "\n----------------------------------------------" + allSales.get(i)
                    + "\n----------------------------------------------" + "\nTOTAL ITEM AMOUNT: " + (getDrinkCount() + getDessertCount()) + "\n");
            this.receipt = "";
        }
    }

    /**
     * "Clears the cash register" which clears each ArrayList containing either DrinkItems or DessertItems
     */
    public void clearCashRegister() {
        drinkItems.clear();
        dessertItems.clear();
    }

    /**
     * Sets the amount of drinks and desserts sold and finds their subtotals
     */
    public void setItemAmmountsAndSubtotals() {
        this.drinkCount = 0;
        this.dessertCount = 0;
        this.drinkSubtotal = 0;
        this.dessertSubtotal = 0;
        for (int i = 0; i < drinkItems.size(); i++) {
            this.drinkSubtotal += drinkItems.get(i).getCost();
            this.drinkCount++;
        }
        for (int i = 0; i < dessertItems.size(); i++) {
            this.dessertSubtotal += dessertItems.get(i).getCost();
            this.dessertCount++;
        }
    }

    /**
     * Gets the amount of drinks sold
     * @return - the amount of drinks sold
     */
    public int getDrinkCount() {
        return this.drinkCount;
    }

    /**
     * Gets the amount of desserts sold
     * @return - the amount of desserts sold
     */
    public int getDessertCount() {
        return this.dessertCount;
    }

    /**
     * Gets the subtotal of all drinks sold
     * @return - the subtotal cost of drink sales
     */
    public void getDrinkSubtotal() {
        this.drinkSubtotal = 0;
        for (int i = 0; i < drinkItems.size(); i++) {
            if (!drinkItems.get(i).getCouponApplied()) {
                this.drinkSubtotal += drinkItems.get(i).getCost();
            }
            else {
                this.drinkSubtotal += drinkItems.get(i).getCouponDiscount();
            }
        }
        drinkSubtotals.add(drinkSubtotal);
    }

    /**
     * Gets the subtotal of all desserts sold
     * @return - the subtotal cost of dessert sales
     */
    public void getDessertSubtotal() {
        this.dessertSubtotal = 0;
        for (int i = 0; i < dessertItems.size(); i++) {
            if (!dessertItems.get(i).getCouponApplied()) {
                this.dessertSubtotal += dessertItems.get(i).getCost();
            }
            else {
                this.dessertSubtotal += dessertItems.get(i).getCouponDiscount();
            }
        }
        dessertSubtotals.add(dessertSubtotal);
    }

    /**
     * Gets the total of all sales made.
     * @return - the final total of all sales made.
     */
    public double totalOfAllSales() {
        this.totalOfAllSales = 0;
        for (int i = 0; i < drinkSubtotals.size(); i++) {
            this.totalOfAllSales += drinkSubtotals.get(i);
        }
        for (int i = 0; i < dessertSubtotals.size(); i++) {
            this.totalOfAllSales += dessertSubtotals.get(i);
        }
        return this.totalOfAllSales;
    }

    /**
     * Gets the final total of a sale
     * @return - the grand total of a sale without tax applied
     */
    public double getGrandTotal() {
        return Math.round(((this.drinkSubtotal + this.dessertSubtotal)) * 100.0) / 100.0;
    }

    /**
     * toString method formats the items sold and their details nicely in a receipt format.
     * Employs polymorphism as the method toString is called on a project2.DrinkItem or a project2.DessertItem object,
     * but it does not explicitly know their specific subclass.
     * @return - a nicely formatted list of items sold and the details of that sale
     */
    public String toString() {
        int orderCount = 0;
        this.receipt = "";
        getDrinkSubtotal();
        getDessertSubtotal();
        for (int i = 0; i < drinkItems.size(); i++) {
            orderCount++;
            this.receipt += "\n" + drinkItems.get(i).toString();
        }
        for (int i = 0; i < dessertItems.size(); i++) {
            orderCount++;
            this.receipt += "\n" + dessertItems.get(i).toString();
        }
        addToAllSales(this.receipt);
        return this.receipt;
    }

    /**
     * Uses the compareTo and max methods of the project2.DrinkItem class to find the largest costing
     * item of the sale, depending on whether the user decides to use their coupon on drinks. Applies
     * coupon to item and applies boolean value to update the Object to have coupon characteristics included.
     * Employs polymorphism as it calls methods on project2.DrinkItem objects, but does not explicitly know
     * their special type.
     * @return - a string detailing the size and name of the drink of highest cost
     */
    public String discountItemDrink() {
        this.couponApplied = false;
        for (int i = 0; i < drinkItems.size(); i++) {
            if (drinkItems.get(i).compareTo(drinkItems.get(i)) > 0) {
                maxItem = drinkItems.get(i);
            }
        }
        if (maxItem.getDrinkName().equals("Coffee")) {
            this.maxCost = maxItem.getCost();
            drinkItems.remove(maxItem);
            maxItem.setCouponApplied(true);
            drinkItems.add(maxItem);
            return maxItem.getSize() + " " + maxItem.getDrinkName();
        }
        else {
            this.maxCost = maxItem.getCost();
            drinkItems.remove(maxItem);
            maxItem.setCouponApplied(true);
            drinkItems.add(maxItem);
            return maxItem.getSize() + " " + maxItem.getBase();
        }
    }

    /**
     * Uses the compareTo and max methods of the project2.DessertItem class to find the largest costing
     * item of the sale, depending on whether the user decides to use their coupon on desserts. Applies
     * coupon to item and applies boolean value to update the Object to have coupon characteristics included.
     * Employs polymorphism as it calls methods on project2.DessertItem objects, but does not explicitly know
     * their special type.
     * @return - a string detailing the size and name of the dessert of highest cost
     */
    public String discountItemDessert() {
        this.couponApplied = false;
        for (int i = 0; i < dessertItems.size(); i++) {
            if (dessertItems.get(i).compareTo(dessertItems.get(i)) == 1) {
                maxItemDessert = dessertItems.get(i);
            }
        }
        this.maxCost = maxItemDessert.getCost();
        dessertItems.remove(maxItemDessert);
        maxItemDessert.setCouponApplied(true);
        dessertItems.add(maxItemDessert);
        return maxItemDessert.getDessertName();
    }

    /**
     * Gets the current max cost of a drink or dessert item
     * @return - cost of highest pricing drink or dessert item
     */
    public double getMaxCost() {
        return this.maxCost;
    }

    /**
     * Gets the current highest pricing drink
     * @return - project2.DrinkItem object representing the highest pricing drink
     */
    public DrinkItem getMaxDrink() {
        return maxItem;
    }

    /**
     * Gets the current highest pricing desset
     * @return - project2.DessertItem object representing the highest pricing dessert
     */
    public DessertItem getMaxDessert() {
        return maxItemDessert;
    }
}
