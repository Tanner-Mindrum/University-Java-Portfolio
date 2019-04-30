package Frames;

import Products.Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * FinalizeOrderFrame is the final frame that appears in this program. This frame appears when the user decides that
 * they are satisfied with their order and want to pay for their order. Contains labels to display how much the user
 * must pay, and updates the amount due until they pay a sufficient amount. Also, contains a text area to display
 * the receipt, and text field to enter payment, and a pay button to submit payment. Finally, it formats the String
 * output nicely in modern receipt format.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class FinalizeOrderFrame extends JFrame {
    private JLabel amountDueLabel;
    private JTextArea receipt;
    private JScrollPane scrollPane;
    private JLabel paymentLabel;
    private JLabel invalidPaymentLabel;
    private JTextField enterPaymentField;
    private JButton payButton;
    private JButton exitButton;
    private JPanel panel;

    private String paymentText;
    private double payment;
    private double theCost;
    private double paymentTotal;
    private String orderList;
    private double totalOrderCost;
    final double TAX = 0.0725;
    private boolean invalidInput;

    private static ArrayList<Item> orders = new ArrayList<Item>();

    DecimalFormat df = new DecimalFormat("0.00");

    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 450;

    public FinalizeOrderFrame(double finalCost, ArrayList<Item> orders) {
        orderList = "";
        createComponents(finalCost, orders);
        this.setTitle("Finalize Order");
    }

    public void createComponents(double finalCost, ArrayList<Item> orders) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        amountDueLabel = new JLabel("Amount Due: $" + df.format(finalCost));

        receipt = new JTextArea(20,50);
        scrollPane = new JScrollPane(receipt);

        for (int i = 0; i < orders.size(); i++) {
            if (orders.get(i) != null) {
                this.orders.add(orders.get(i));
            }
        }

        for (int i = 0; i < this.orders.size(); i++) {
            if (this.orders.get(i) != null) {
                totalOrderCost += this.orders.get(i).getCost();
                orderList += this.orders.get(i).toString() + "\n\n";
            }
        }
        receipt.setText("                              ~~~~~~~~~~~~~~~~~~~~~~~   Your Order  ~~~~~~~~~~~~~~~~~~~~~\n\n"
                + orderList +
                "\t        Subtotal:\t\t\t                  $" + df.format(totalOrderCost) +
                "\n\t        Tax:\t\t\t                  $" + df.format(totalOrderCost * TAX) +
                "\n\t       ________________________________________________" +
                "\n\t        Grand total:\t\t\t                  $" + df.format(totalOrderCost + totalOrderCost * TAX) + "\n\n");

        receipt.setEditable(false);

        paymentLabel = new JLabel("Payment: $");
        enterPaymentField = new JTextField(15);

        invalidPaymentLabel = new JLabel("Payment not accepted, invalid input.");

        payButton = new JButton("Pay");
        payButton.addActionListener(new ButtonListener());
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ButtonListener());

        panel = new JPanel();
        panel.add(amountDueLabel);
        panel.add(scrollPane);
        panel.add(paymentLabel);
        panel.add(enterPaymentField);
        panel.add(payButton);
        this.add(panel);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            invalidInput = false;
            if (click.getSource() == payButton) {
                paymentText = enterPaymentField.getText().trim();
                try {
                    payment = Double.parseDouble(paymentText);
                }
                catch (NumberFormatException n) {
                    invalidInput = true;
                }
                paymentTotal += payment;
                theCost = Double.parseDouble(df.format(totalOrderCost + totalOrderCost * TAX));
                if (paymentTotal < theCost && !invalidInput) {
                    amountDueLabel.setText("<html><span bgcolor=\"yellow\">Insufficient payment! Amount still due: $"
                            + (df.format(((totalOrderCost + totalOrderCost * TAX) - payment))) + "</span></html>");
                }
                else if (invalidInput) {
                    amountDueLabel.setText("<html><span bgcolor=\"#EC7063\">Invalid input! Amount still due: $"
                            + (df.format(((totalOrderCost + totalOrderCost * TAX) - payment))) + "</span></html>");
                }
                else {
                    amountDueLabel.setText("<html><span bgcolor=\"90EE90\">Thank you!</span></html>");

                    receipt.setText("                              ~~~~~~~~~~~~~~~~~~~~~~ Your Order  ~~~~~~~~~~~~~~~~~~~~~\n\n"
                            + orderList +
                            "\t        Subtotal:\t\t\t                  $" + df.format(totalOrderCost) +
                            "\n\t        Tax:\t\t\t                  $" + df.format(totalOrderCost * TAX) +
                            "\n\t       ________________________________________________" +
                            "\n\t        Grand total:\t\t\t                  $" +
                            df.format(totalOrderCost + totalOrderCost * TAX) +
                            "\n\n\t        Payment:\t\t\t                  $" + df.format(paymentTotal) +
                            "\n\t        Change Due:\t\t                  $" +
                            df.format((paymentTotal - (theCost))));
                    panel.remove(paymentLabel);
                    panel.remove(enterPaymentField);
                    panel.remove(payButton);
                    panel.repaint();
                    panel.add(exitButton);
                    panel.revalidate();
                }
            }
            else if (click.getSource() == exitButton) {
                System.exit(0);
            }
        }
    }
}
