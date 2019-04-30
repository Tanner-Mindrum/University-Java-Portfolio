package Frames;

import Products.CoffeeItem;
import Products.DrinkItem;
import Products.Item;
import Products.PastryItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * InitialFrame is the first frame that appears in the program. Furthermore, it can be considered the "main frame"
 * because once the user purchases an item, they return back to this frame, which allows them to either purchase
 * more items, pay for their order, or, in the case that they decide not to buy anything, exit the program without
 * purchasing an item. Contains buttons that allow the user to purchase coffee, tea, or pastries, and a done button
 * to either pay for their order or exit the program. Finally, it formats the String output nicely in modern receipt
 * format.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class InitialFrame extends JFrame {
    private JFrame coffeeFrame;
    private JFrame teaFrame;
    private JFrame pastryFrame;
    private JFrame finalFrame;
    private JLabel label;
    private JButton done;
    private JButton coffeeButton;
    private JButton teaButton;
    private JButton pastryButton;
    private JPanel panel;
    private JTextArea receipt;
    private JScrollPane scrollPane;

    DecimalFormat df = new DecimalFormat("0.00");

    private static int frameCalledTwice;
    private static int noPurchase;
    private boolean purchaseMade;
    private String orderList;
    private double totalOrderCost;
    final double TAX = 0.0725;

    private static ArrayList<Item> orders = new ArrayList<Item>();

    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 200;

    public InitialFrame(boolean purchase, DrinkItem drink, PastryItem pastry) {
        this.purchaseMade = purchase;
        orderList = "";
        createComponents(drink, pastry);
        this.setTitle("New Order");

        coffeeFrame = new CoffeeFrame();
        teaFrame = new TeaFrame();
        pastryFrame = new PastryFrame();
    }

    public void createComponents(DrinkItem drinkOrders, PastryItem pastryOrders) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        label = new JLabel("Select an item to purchase: ");

        coffeeButton = new JButton("Coffee");
        coffeeButton.addActionListener(new ButtonListener());
        teaButton = new JButton("Tea");
        teaButton.addActionListener(new ButtonListener());
        pastryButton = new JButton("Pastry");
        pastryButton.addActionListener(new ButtonListener());
        done = new JButton("Done");
        done.addActionListener(new ButtonListener());

        receipt = new JTextArea(20,50);
        scrollPane = new JScrollPane(receipt);

        if (purchaseMade || frameCalledTwice > 0) {
            frameCalledTwice++;
            orders.add(drinkOrders);
            orders.add(pastryOrders);
            for (int i = 0; i < orders.size(); i++) {
                if (orders.get(i) != null) {
                totalOrderCost += orders.get(i).getCost();
                orderList += orders.get(i).toString() + "\n\n";
                }
            }
            receipt.setText("                              ~~~~~~~~~~~~~~~~~~~~~  Current Order  ~~~~~~~~~~~~~~~~~~~~~\n\n"
                    + orderList +
                    "\t        Subtotal:\t\t\t                  $" + df.format(totalOrderCost) +
                    "\n\t        Tax:\t\t\t                  $" + df.format(totalOrderCost * TAX) +
                    "\n\t       ________________________________________________" +
                    "\n\t        Grand total:\t\t\t                  $" + df.format(totalOrderCost + totalOrderCost * TAX) + "\n\n");
        }
        else {
            noPurchase++;
        }

        receipt.setEditable(false);

        panel = new JPanel();
        panel.add(label);
        panel.add(coffeeButton);
        panel.add(teaButton);
        panel.add(pastryButton);
        if (purchaseMade || frameCalledTwice > 0) {
            panel.add(scrollPane);
            panel.add(done);
            setSize(600, 450);
        }
        else if (noPurchase > 1) {
            panel.add(done);
        }
        this.add(panel);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            if (click.getSource() == coffeeButton) {
                setVisible(false);
                coffeeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                coffeeFrame.setVisible(true);
            }
            else if (click.getSource() == teaButton) {
                setVisible(false);
                teaFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                teaFrame.setVisible(true);
            }
            else if (click.getSource() == pastryButton) {
                setVisible(false);
                pastryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pastryFrame.setVisible(true);
            }
            else if (click.getSource() == done){
                if (noPurchase > 1 && !purchaseMade) {
                    System.exit(0);
                }
                else {
                    finalFrame = new FinalizeOrderFrame(totalOrderCost + totalOrderCost * TAX, orders);

                    setVisible(false);
                    finalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    finalFrame.setVisible(true);
                }
            }
        }
    }
}