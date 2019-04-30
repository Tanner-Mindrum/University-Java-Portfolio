package Frames;

import Products.CoffeeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The CoffeeFrame is the frame that appears if the user decides to purchase coffee. It contains drop downs and buttons
 * to place a complete coffee order. Also, it contains a text field for the user to enter special instructions for
 * their drink. The user has the option to either save their order or cancel at anytime. After purchase or cancellation,
 * the user will be returned to the main frame.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class CoffeeFrame extends JFrame {
    private JLabel label;
    private JLabel flavorLabel;
    private JLabel sizeLabel;
    private JLabel sugarLabel;
    private JLabel milkLabel;
    private JLabel typeLabel;
    private JLabel specialInstructionsLabel;
    private JComboBox<String> flavor;
    private JComboBox<String> size;
    private JComboBox<String> sugar;
    private JComboBox<String> milk;
    private JComboBox<String> type;
    private JTextField specialInstructions;
    private JButton save;
    private JButton cancel;
    private JPanel panel;

    private CoffeeItem coffeeOrder;

    private String flavorOfChoice;
    private String sizeOfChoice;
    private String sugarOfChoice;
    private String milkOfChoice;
    private String typeOfChoice;
    private String specialInsrtuctionsOfChoice;

    private static int saveCount;

    private final String[] flavors = {"Regular", "Mocha", "Hazelnut", "Vanilla"};
    private final String[] sizes = {"S", "M", "L"};
    private final String[] sweetnesses = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private final String[] milks = {"whole milk", "half-and-half", "no milk"};
    private final String[] types = {"hot", "iced", "blended"};

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 400;

    public CoffeeFrame() {
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("New Coffee Order");
    }


    public void createComponents() {
        label = new JLabel("Specify the coffee order: ");
        flavorLabel = new JLabel("Flavor: ");
        sizeLabel = new JLabel("Size: ");
        sugarLabel = new JLabel("Sugar: ");
        milkLabel = new JLabel("Milk: ");
        typeLabel = new JLabel("Type: ");
        specialInstructionsLabel = new JLabel("Special instructions: ");

        flavor = new JComboBox<String>(flavors);
        flavor.addActionListener(new ButtonListener());
        size = new JComboBox<String>(sizes);
        size.addActionListener(new ButtonListener());
        sugar = new JComboBox<String>(sweetnesses);
        sugar.addActionListener(new ButtonListener());
        milk = new JComboBox<String>(milks);
        milk.addActionListener(new ButtonListener());
        type = new JComboBox<String>(types);
        type.addActionListener(new ButtonListener());

        specialInstructions = new JTextField(50);

        save = new JButton("Save");
        save.addActionListener(new ButtonListener());
        cancel = new JButton("Cancel");
        cancel.addActionListener(new ButtonListener());

        panel = new JPanel();
        panel.add(label);
        panel.add(flavorLabel);
        panel.add(flavor);
        panel.add(sizeLabel);
        panel.add(size);
        panel.add(sugarLabel);
        panel.add(sugar);
        panel.add(milkLabel);
        panel.add(milk);
        panel.add(typeLabel);
        panel.add(type);
        panel.add(specialInstructionsLabel);
        panel.add(specialInstructions);
        panel.add(save);
        panel.add(cancel);
        this.add(panel);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            if (click.getSource() == save) {
                saveCount++;

                flavorOfChoice = (String) flavor.getSelectedItem();
                sizeOfChoice = (String) size.getSelectedItem();
                sugarOfChoice = (String) sugar.getSelectedItem();
                milkOfChoice = (String) milk.getSelectedItem();
                typeOfChoice = (String) type.getSelectedItem();
                specialInsrtuctionsOfChoice = specialInstructions.getText().trim();

                coffeeOrder = new CoffeeItem("Coffee", sizeOfChoice, flavorOfChoice, sugarOfChoice, milkOfChoice, typeOfChoice, specialInsrtuctionsOfChoice);
                coffeeOrder.calculateCost();

                InitialFrame newFrame = new InitialFrame(true, coffeeOrder, null);
                setVisible(false);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
            }
            else if (click.getSource() == cancel) {
                if (saveCount == 0) {
                    InitialFrame newFrame = new InitialFrame(false, null, null);
                    setVisible(false);
                    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    newFrame.setVisible(true);
                }
                else {
                    InitialFrame newFrame = new InitialFrame(true, null, null);
                    setVisible(false);
                    newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    newFrame.setVisible(true);
                }
            }
        }
    }
}