package Frames;

import Products.TeaItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The TeaFrame is the frame that appears if the user decides to purchase coffee. It contains drop downs, buttons, and
 * check boxes to place a complete tea order. The user has the option to either save their order or cancel at anytime.
 * After purchase or cancellation, the user will be returned to the main frame.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class TeaFrame extends JFrame {
    private JLabel specifyOrderLabel;
    private JComboBox<String> flavorDropDown;
    private JLabel sizeLabel;
    private JComboBox<String> sizeDropDown;
    private JLabel sweetnessLabel;
    private JComboBox<String> sweetnessDropDown;
    private JLabel milkLabel;
    private JComboBox<String> milkDropDown;
    private JButton saveButton;
    private JButton cancelButton;
    private ArrayList<JCheckBox> toppingCheckBoxes;
    private JCheckBox bobaToppingBox;
    private JCheckBox poppingBobaToppingBox;
    private JCheckBox grassJellyToppingBox;
    private JCheckBox lycheeJellyToppingBox;
    private JCheckBox coconutJellyToppingBox;
    private JCheckBox miniMochiToppingBox;
    private JPanel panel;

    private final String [] sizes = {"S", "M", "L"};
    private final String [] sweetnesses = {"full", "3/4 sweet", "1/2 sweet", "1/4 sweet", "unsweetened"};
    private final String[] flavors = {"Green Tea", "Black Tea", "Jasmine Green Tea", "Rose Tea", "Oolong Tea"};
    private final String[] milks = {"whole milk", "half-and-half", "no milk"};
    private final String[] toppings = {"boba", "popping boba", "grass jelly", "lychee jelly", "coconut jelly", "mini mochi"};

    private String sizeOfChoice;
    private String sweetnessOfChoice;
    private String flavorOfChoice;
    private String milkOfChoice;
    private String toppingsOfChoice;

    private static int saveCount;

    private TeaItem teaOrder;

    private static final int FRAME_WIDTH = 850;
    private static final int FRAME_HEIGHT = 400;

    public TeaFrame() {
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("New Tea Order");
    }

    public void createComponents() {
        specifyOrderLabel = new JLabel("Specify the tea order: ");
        flavorDropDown = new JComboBox<String>(flavors);
        flavorDropDown.addActionListener(new ButtonListener());

        sizeLabel = new JLabel("Size: ");
        sizeDropDown = new JComboBox<String>(sizes);
        sizeDropDown.addActionListener(new ButtonListener());

        sweetnessLabel = new JLabel("Sweetness: ");
        sweetnessDropDown = new JComboBox<String>(sweetnesses);
        sweetnessDropDown.addActionListener(new ButtonListener());

        milkLabel = new JLabel("Milk: ");
        milkDropDown = new JComboBox<String>(milks);
        milkDropDown.addActionListener(new ButtonListener());

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ButtonListener());
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ButtonListener());

        bobaToppingBox = new JCheckBox("Boba");
        bobaToppingBox.addActionListener(new ButtonListener());
        poppingBobaToppingBox = new JCheckBox("Popping Boba");
        poppingBobaToppingBox.addActionListener(new ButtonListener());
        grassJellyToppingBox = new JCheckBox("Grass Jelly");
        grassJellyToppingBox.addActionListener(new ButtonListener());
        lycheeJellyToppingBox = new JCheckBox("Lychee Jelly");
        lycheeJellyToppingBox.addActionListener(new ButtonListener());
        coconutJellyToppingBox = new JCheckBox("Coconut Jelly");
        coconutJellyToppingBox.addActionListener(new ButtonListener());
        miniMochiToppingBox = new JCheckBox("Mini Mochi");
        miniMochiToppingBox.addActionListener(new ButtonListener());

        toppingCheckBoxes = new ArrayList<JCheckBox>();
        toppingCheckBoxes.add(bobaToppingBox);
        toppingCheckBoxes.add(poppingBobaToppingBox);
        toppingCheckBoxes.add(grassJellyToppingBox);
        toppingCheckBoxes.add(lycheeJellyToppingBox);
        toppingCheckBoxes.add(coconutJellyToppingBox);
        toppingCheckBoxes.add(miniMochiToppingBox);

        panel = new JPanel();
        panel.add(specifyOrderLabel);
        panel.add(flavorDropDown);
        panel.add(sizeLabel);
        panel.add(sizeDropDown);
        panel.add(sweetnessLabel);
        panel.add(sweetnessDropDown);
        panel.add(milkLabel);
        panel.add(milkDropDown);
        panel.add(saveButton);
        panel.add(cancelButton);
        panel.add(bobaToppingBox);
        panel.add(poppingBobaToppingBox);
        panel.add(grassJellyToppingBox);
        panel.add(lycheeJellyToppingBox);
        panel.add(coconutJellyToppingBox);
        panel.add(miniMochiToppingBox);
        this.add(panel);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            if (click.getSource() == saveButton) {
                saveCount++;

                flavorOfChoice = (String) flavorDropDown.getSelectedItem();
                sizeOfChoice = (String) sizeDropDown.getSelectedItem();
                sweetnessOfChoice = (String) sweetnessDropDown.getSelectedItem();
                milkOfChoice = (String) milkDropDown.getSelectedItem();

                teaOrder = new TeaItem(sizeOfChoice, flavorOfChoice, sweetnessOfChoice, milkOfChoice);
                for (int i = 0; i < toppingCheckBoxes.size(); i++) {
                    if (toppingCheckBoxes.get(i).isSelected()) {
                        teaOrder.addTopping(toppings[i]);
                    }
                }
                teaOrder.calculateCost();

                InitialFrame newFrame = new InitialFrame(true, teaOrder, null);
                setVisible(false);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
            }
            else if (click.getSource() == cancelButton) {
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