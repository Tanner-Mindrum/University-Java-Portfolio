import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        int choice;
        int drinkOfChoice;
        int sizeOfChoice;
        int sweetnessOfChoice;
        int baseOfChoice;
        int toppingOfChoice;
        int milkOfChoice;
        int quantity;
        int heatChoice;
        double payment;
        double change;
        double couponAmount = 0;
        String couponType = "";
        int couponCount = 0;
        ArrayList<Integer> toppingChoices = new ArrayList<Integer>();
        double total = 0;
        double totalWithTax = 0;
        final double TAX = 0.0725;
        DecimalFormat df = new DecimalFormat("0.00");
        CashRegister cashRegister = new CashRegister();
        String discountDrinkName;
        String discountDessertName;
        double discountDrinkPrice;
        double discountDessertPrice;
        int discountDessertQuantity;


        boolean helpingCustomer = true;

        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to Boba & Java!\n");

        while (helpingCustomer) {
            boolean makingASale = true;
            //couponCount++;

            System.out.println("Enter a number to select an option:");
            System.out.println("1. Make a new sale");
            System.out.println("2. Print all sales");
            System.out.println("3. Close the cash register\n");

            while (true) {
                System.out.println("Enter option: ");
                choice = in.nextInt();
                if (choice < 1 || choice > 3){
                    System.out.println("That option does not exist, try again.\n");
                }
                else {
                    break;
                }
            }

            if (choice == 1) {
                while (makingASale) {
                    boolean choosingTopping = true;
                    int toppingCount = 0;
                    CoffeeDrink newCoffee = new CoffeeDrink();
                    BobaDrink newBoba = new BobaDrink();
                    Pastry newPastry = new Pastry();
                    Cookie newCookie = new Cookie();
                    Macaroon newMacaroon = new Macaroon();
                    System.out.println("\nWhat would you like to do?");
                    System.out.println("1. Purchase a drink");
                    System.out.println("2. Purchase pastries");
                    System.out.println("3. Purchase cookies");
                    System.out.println("4. Purchase macaroons");
                    System.out.println("5. Finalize sale\n");

                    while (true) {
                        System.out.println("Enter option: ");
                        choice = in.nextInt();
                        if (choice < 1 || choice > 5) {
                            System.out.println("That option does not exist, try again.\n");
                        }
                        else {
                            break;
                        }
                    }

                    if (choice == 1) {
                        System.out.println("\nWhat kind of drink would you like?");
                        System.out.println("1. Coffee");
                        System.out.println("2. Tea\n");

                        while (true) {
                            System.out.println("Enter option: ");
                            choice = in.nextInt();
                            if (choice < 1 || choice > 2) {
                                System.out.println("That option does not exist, try again.\n");
                            }
                            else {
                                break;
                            }
                        }

                        if (choice == 1) {
                            //Coffee stuff
                            newCoffee.setDrinkName("Coffee");
                            System.out.println("\nEnter size:\n1. Small ($3.75)\n2. Medium ($4.50)\n3. Large ($5.75)\n");
                            while (true) {
                                System.out.println("Enter option: ");
                                sizeOfChoice = in.nextInt();
                                if (newCoffee.validateOption(sizeOfChoice)) {
                                    break;
                                }
                            }
                            newCoffee.setSize(Integer.toString(sizeOfChoice));

                            System.out.println("\nEnter sweetness (tsps of sugar): ");
                            sweetnessOfChoice = in.nextInt();
                            newCoffee.setSweetness(Integer.toString(sweetnessOfChoice));

                            System.out.println("\nEnter base:\n1. Water\n2. Whole milk\n3. Almond milk\n ");
                            while (true) {
                                System.out.println("Enter option: ");
                                baseOfChoice = in.nextInt();
                                if (newCoffee.validateOption(baseOfChoice)) {
                                    break;
                                }
                            }
                            newCoffee.setBase(Integer.toString(baseOfChoice));

                            //Receipt
                            cashRegister.addDrink(newCoffee);

                        }
                        else if (choice == 2) {
                            //Tea stuff
                            newBoba.setDrinkName("Boba tea");
                            System.out.println("\nEnter size:\n1. Small ($3.75)\n2. Medium ($4.50)\n3. Large ($5.75)\n");
                            while (true) {
                                System.out.println("Enter option: ");
                                sizeOfChoice = in.nextInt();
                                if (newBoba.validateOption(sizeOfChoice)) {
                                    break;
                                }
                            }
                            newBoba.setSize(Integer.toString(sizeOfChoice));

                            System.out.println("\nEnter sweetness:\n1. Unsweetened\n2. 1/4 sweet\n3. 1/2 sweet\n" +
                                    "4. 3/4 sweet\n5. Full\n");
                            while (true) {
                                System.out.println("Enter option: ");
                                sweetnessOfChoice = in.nextInt();
                                if (!(sweetnessOfChoice < 1 || sweetnessOfChoice > 5)) {
                                    break;
                                }
                                else {
                                    System.out.println("That option does not exist, try again.\n");
                                }
                            }
                            newBoba.setSweetness(Integer.toString(sweetnessOfChoice));

                            System.out.println("\nEnter milk:\n1. Whole milk\n2. Half-and-half\n3. Almond milk\n" +
                                    "4. Coconut milk\n5. No milk");
                            while (true) {
                                System.out.println("Enter option: ");
                                milkOfChoice = in.nextInt();
                                if (!(milkOfChoice < 1 || milkOfChoice > 5)) {
                                    break;
                                }
                                else {
                                    System.out.println("That option does not exist, try again.\n");
                                }
                            }
                            newBoba.setMilk(milkOfChoice);

                            System.out.println("\nEnter toppings ($0.25 per topping):\n1. No toppings/Finish selecting toppings\n2. Boba\n" +
                                    "3. Popping boba\n4. Grass jelly\n5. Lychee jelly\n6. Coconut jelly\n" +
                                    "7. Mini mochi");
                            while (choosingTopping) {
                                System.out.println("Enter option: ");
                                toppingOfChoice = in.nextInt();
                                if (toppingOfChoice == 1) {
                                    if (!(toppingCount == 0)) {
                                        System.out.println("You added: " + newBoba.getToppings());
                                    }
                                    choosingTopping = false;
                                }
                                else if (!(toppingOfChoice < 1 || toppingOfChoice > 7)) {
                                    if (toppingChoices.contains(toppingOfChoice)) {
                                        System.out.println("You already chose that topping!");
                                    }
                                    else {
                                        newBoba.addToppings(toppingOfChoice);
                                        toppingChoices.add(toppingOfChoice);
                                        toppingCount++;
                                        System.out.println("Topping added!");
                                    }
                                }
                                else {
                                    System.out.println("That option does not exist, try again.\n");
                                }
                            }

                            System.out.println("\nEnter base:\n1. Green tea\n2. Black tea\n3. Jasmine green tea\n4. Rose tea\n5. Oolong tea");
                            while (true) {
                                System.out.println("Enter option: ");
                                baseOfChoice = in.nextInt();
                                if (!(baseOfChoice < 1 || baseOfChoice > 5)) {
                                    break;
                                }
                                else {
                                    System.out.println("That option does not exist, try again.\n");
                                }
                            }
                            newBoba.setBases(Integer.toString(baseOfChoice));

                            toppingChoices.clear();

                            //Receipt
                            cashRegister.addDrink(newBoba);
                        }


                    }
                    else if (choice == 2) {
                        //Pastries stuff
                        System.out.println("\nWhat kind of pastry would you like?\n1. Cinnamon Roll ($3.00)\n" +
                                "2. Muffin ($2.50)");
                        while (true) {
                            System.out.println("Enter option: ");
                            choice = in.nextInt();
                            if (choice < 1 || choice > 2) {
                                System.out.println("That option does not exist, try again.\n");
                            }
                            else {
                                break;
                            }
                        }
                        if (choice == 1) {
                            //cinnamon roll
                            newPastry.setDessertName("Cinnamon roll");
                        }
                        else {
                            newPastry.setDessertName("Muffin");
                        }

                        while (true) {
                            System.out.println("\nEnter quantity: ");
                            quantity = in.nextInt();
                            if (quantity > 0) {
                                break;
                            }
                            else {
                                System.out.println("Invalid input, please try again.");
                            }
                        }
                        newPastry.setQuantity(quantity);

                        System.out.println("\nWould you like to heat your " + newPastry.getDessertName() + "? ($0.25)\n" +
                                "1. Heat up\n2. Don't heat");
                        while (true) {
                            System.out.println("Enter option: ");
                            heatChoice = in.nextInt();
                            if (heatChoice < 1 || heatChoice > 2) {
                                System.out.println("That option does not exist, try again.\n");
                            }
                            else {
                                break;
                            }
                        }
                        newPastry.setHeating(heatChoice);

                        //Receipt
                        cashRegister.addDessert(newPastry);

                    }
                    else if (choice == 3) {
                        //Cookies stuff
                        System.out.println("\nWhat kind of cookie would you like?\n1. Oatmeal raisin ($2.00 for one, $12.00 per dozen)\n" +
                                "2. White chocolate chunk ($2.50 for one, $15.00 per dozen)");
                        while (true) {
                            System.out.println("Enter option: ");
                            choice = in.nextInt();
                            if (choice < 1 || choice > 2) {
                                System.out.println("That option does not exist, try again.\n");
                            }
                            else {
                                break;
                            }
                        }
                        if (choice == 1) {
                            newCookie.setDessertName("Oatmeal raisin");
                        }
                        else {
                            newCookie.setDessertName("White chocolate chunk");
                        }

                        while (true) {
                            System.out.println("\nEnter quantity: ");
                            quantity = in.nextInt();
                            if (quantity > 0) {
                                break;
                            }
                            else {
                                System.out.println("Invalid input, please try again.");
                            }
                        }
                        newCookie.setQuantity(quantity);

                        //Receipt
                        cashRegister.addDessert(newCookie);

                    }
                    else if (choice == 4) {
                        //Macaroons stuff
                        System.out.println("\nWhat kind of macaroon would you like?\n1. Orange macaroon" +
                                " ($1.50 for one, $3.00 for three)\n2. Cherry macaroon" +
                                "($1.00 for one, $2.00 for three)");
                        while (true) {
                            System.out.println("Enter option: ");
                            choice = in.nextInt();
                            if (choice < 1 || choice > 2) {
                                System.out.println("That option does not exist, try again.\n");
                            }
                            else {
                                break;
                            }
                        }
                        if (choice == 1) {
                            newMacaroon.setDessertName("Orange macaroon");
                        }
                        else {
                            newMacaroon.setDessertName("Cherry macaroon");
                        }

                        while (true) {
                            System.out.println("\nEnter quantity: ");
                            quantity = in.nextInt();
                            if (quantity > 0) {
                                break;
                            }
                            else {
                                System.out.println("Invalid input, please try again.");
                            }
                        }
                        newMacaroon.setQuantity(quantity);

                        //Receipt
                        cashRegister.addDessert(newMacaroon);

                    }
                    else if (choice == 5) {
                        //Finalize sale stuff
                        boolean workingWithCoupon = true;
                        cashRegister.setItemAmmountsAndSubtotals();
                        if (cashRegister.getGrandTotal() != 0) {
                            total = cashRegister.getGrandTotal();
                            System.out.println("\nDo you have any coupons?\nIf so, of what type?\n1. No coupons\n2. Drink coupon\n3. Dessert coupons");
                            while (workingWithCoupon) {
                                while (true) {
                                    System.out.println("Enter option: ");
                                    choice = in.nextInt();
                                    if (choice < 1 || choice > 3) {
                                        System.out.println("That option does not exist, try again.\n");
                                    } else {
                                        break;
                                    }
                                }
                                if (choice == 1) {
                                    workingWithCoupon = false;
                                    break;
                                }
                                else if (choice == 2) {
                                    couponType = "drink";
                                }
                                else {
                                    couponType = "dessert";
                                }
                                while (true) {
                                    System.out.println("Enter coupon amount: ");
                                    couponAmount = in.nextDouble();
                                    if (couponAmount > 0) {
                                        break;
                                    } else {
                                        System.out.println("Invalid input, try again.\n");
                                    }
                                }
                                Coupon newCoupon = new Coupon(couponType, couponAmount);
                                System.out.println("\n" + newCoupon.toString());
                                if (choice == 2) {
                                    if (cashRegister.getDrinkCount() != 0) {
                                        discountDrinkName = cashRegister.discountItemDrink();
                                        discountDrinkPrice = cashRegister.getMaxCost();
                                        System.out.println("Drink item with largest cost: " + discountDrinkName + " @ $" + df.format(discountDrinkPrice));
                                        System.out.println("\nApply coupon?\n1. Apply coupon\n2. Don't apply coupon");
                                        while (true) {
                                            System.out.println("Enter option: ");
                                            choice = in.nextInt();
                                            if (choice < 1 || choice > 2) {
                                                System.out.println("That option does not exist, try again.\n");
                                            } else {
                                                break;
                                            }
                                        }
                                        if (choice == 1) {
                                            total = total - discountDrinkPrice;
                                            System.out.println("\nThat item now costs: $" +
                                                    df.format((discountDrinkPrice - (discountDrinkPrice * newCoupon.getDiscount())))
                                                    + "\nYou saved: $" + df.format((discountDrinkPrice * newCoupon.getDiscount())));
                                            cashRegister.getMaxDrink().setCouponDiscount((discountDrinkPrice - (discountDrinkPrice * newCoupon.getDiscount())));
                                            total = total + (discountDrinkPrice - (discountDrinkPrice * newCoupon.getDiscount()));
                                            workingWithCoupon = false;
                                            break;
                                        }
                                        else {
                                            cashRegister.getMaxDrink().setCouponApplied(false);
                                            workingWithCoupon = false;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println("\nCannot use coupon!\nYou have not purchased any drinks!");
                                        workingWithCoupon = false;
                                        break;
                                    }
                                }
                                if (choice == 3) {
                                    if (cashRegister.getDessertCount() != 0) {
                                        discountDessertName = cashRegister.discountItemDessert();
                                        discountDessertPrice = cashRegister.getMaxCost();
                                        discountDessertQuantity = cashRegister.getMaxDessert().getQuantity();
                                        System.out.println("Dessert item with largest cost: " + discountDessertQuantity + "x " + discountDessertName + " @ $" + df.format(discountDessertPrice));
                                        System.out.println("\nApply coupon?\n1. Apply coupon\n2. Don't apply coupon");
                                        while (true) {
                                            System.out.println("Enter option: ");
                                            choice = in.nextInt();
                                            if (choice < 1 || choice > 2) {
                                                System.out.println("That option does not exist, try again.\n");
                                            } else {
                                                break;
                                            }
                                        }
                                        if (choice == 1) {
                                            total = total - discountDessertPrice;
                                            System.out.println("\nThat item now costs: $" +
                                                    df.format((discountDessertPrice - (discountDessertPrice * newCoupon.getDiscount())))
                                                    + "\nYou saved: $" + df.format((discountDessertPrice * newCoupon.getDiscount())));
                                            cashRegister.getMaxDessert().setCouponDiscount((discountDessertPrice - (discountDessertPrice * newCoupon.getDiscount())));
                                            total = total + (discountDessertPrice - (discountDessertPrice * newCoupon.getDiscount()));
                                            workingWithCoupon = false;
                                            break;
                                        }
                                        else {
                                            cashRegister.getMaxDessert().setCouponApplied(false);
                                            workingWithCoupon = false;
                                            break;
                                        }
                                    }
                                    else {
                                        System.out.println("\nCannot use coupon!\nYou have not purchased any desserts!");
                                        workingWithCoupon = false;
                                        break;
                                    }
                                }
                            }

                            System.out.println("\nSubtotal: $" + df.format(total));
                            totalWithTax = ((total * TAX) + total);
                            System.out.println("Total after tax: $" + df.format((total * TAX) + total));
                            while (true) {
                                System.out.println("\nEnter payment: ");
                                payment = in.nextDouble();
                                change = payment - totalWithTax;
                                if (change >= 0) {
                                    break;
                                } else {
                                    System.out.println("Insufficient funds, try again!");
                                }
                            }
                            System.out.print("\nReceipt:\n----------------------------------------------");
                            System.out.println(cashRegister.toString());
                            System.out.println("----------------------------------------------");
                            System.out.printf("%46s", "SUBTOTAL: $" + df.format(total));
                            System.out.println();
                            System.out.printf("%46s", "CA Tax: $" + df.format(totalWithTax - total));
                            System.out.println();
                            System.out.printf("%46s", "TOTAL: $" + df.format(totalWithTax));
                            System.out.println("\n");
                            System.out.println("Here is your change: $" + df.format(change) + "\n");
                            cashRegister.clearCashRegister();
                            makingASale = false;
                        }
                        else {
                            System.out.println("No sales have been made!\n");
                            makingASale = false;
                        }
                    }
                }
            }
            else if (choice == 2) {
                if (cashRegister.getAllSalesSize() == 0) {
                    System.out.println("No sales have been made!\n");
                }
                else {
                    System.out.println("All Sales:\n");
                    cashRegister.displayAllsales();
                }
            }
            else if (choice == 3) {
                System.out.println("\nClosing cash register.");
                helpingCustomer = false;
            }
        }
    }
}
