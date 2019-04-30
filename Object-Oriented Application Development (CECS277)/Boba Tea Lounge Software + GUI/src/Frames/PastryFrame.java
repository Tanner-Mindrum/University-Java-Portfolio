package Frames;

import Products.PastryItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The PastryFrame is the frame that appears if the user decides to purchase a pastry. It contains drop downs, buttons,
 * and check boxes to place a complete tea order. First, the user chooses their pastry type using a drop down menu.
 * Then, a new drop down appears with flavors specific to their pastry of choice. The user has the option to either
 * save their order or cancel at anytime. After purchase or cancellation, the user will be returned to the main frame.
 * @author - Tanner Mindrum
 * @since - 2019-03-17
 */
public class PastryFrame extends JFrame {
    private JComboBox<String> pastryTypeDropDown;
    private JComboBox<String> muffinFlavorsDropDown;
    private JComboBox<String> cookieFlavorsDropDown;
    private JComboBox<String> cheesecakeSliceFlavorsDropDown;
    private JComboBox<String> danishFlavorsDropDown;
    private JLabel heatedLabel;
    private JCheckBox heatedBox;
    private JButton cancelButton;
    private JButton saveButton;
    private JPanel panel;

    private String pastryOfChoice;
    private String flavorOfChoice;
    private boolean heatChoice;

    private PastryItem pastryOrder;

    private static int saveCount;

    private final String[] pastryTypes = {"Muffin", "Cookie", "Cheesecake slice", "Danish"};
    private final String[] muffinFlavors = {"Banana nut", "Blueberry", "Chocolate chip", "Coffee Cake"};
    private final String[] cookieFlavors = {"Oatmeal", "White choco & Macadamias", "Chocolate chip", "Double fudge"};
    private final String[] cheesecakeSliceFlavors = {"Regular", "Cherry", "Blueberry"};
    private final String[] danishFlavors = {"Apple Cinnamon", "Strawberry & Cheese", "Double Cheese"};

    private static final int FRAME_WIDTH = 850;
    private static final int FRAME_HEIGHT = 400;

    public PastryFrame() {
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("New Pastry Order");
    }

    public void createComponents() {
        pastryTypeDropDown = new JComboBox<String>(pastryTypes);
        pastryTypeDropDown.addActionListener(new ButtonListener());

        muffinFlavorsDropDown = new JComboBox<String>(muffinFlavors);
        muffinFlavorsDropDown.addActionListener(new ButtonListener());
        cookieFlavorsDropDown = new JComboBox<String>(cookieFlavors);
        cookieFlavorsDropDown.addActionListener(new ButtonListener());
        cheesecakeSliceFlavorsDropDown = new JComboBox<String>(cheesecakeSliceFlavors);
        cheesecakeSliceFlavorsDropDown.addActionListener(new ButtonListener());
        danishFlavorsDropDown = new JComboBox<String>(danishFlavors);
        danishFlavorsDropDown.addActionListener(new ButtonListener());

        heatedLabel = new JLabel("Heat up?: ");
        heatedBox = new JCheckBox();

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ButtonListener());
        saveButton = new JButton("Save");
        saveButton.addActionListener(new ButtonListener());

        panel = new JPanel();
        panel.add(pastryTypeDropDown);
        panel.add(cancelButton);
        this.add(panel);
    }

    class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent click) {
            pastryOfChoice = (String) pastryTypeDropDown.getSelectedItem();
            heatChoice = false;
            if (click.getSource() == cancelButton) {
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
            else if (click.getSource() == saveButton) {
                saveCount++;

                if (heatedBox.isSelected()) {
                    heatChoice = true;
                }
                pastryOrder = new PastryItem(pastryOfChoice, flavorOfChoice, heatChoice);
                pastryOrder.calculateCost();

                InitialFrame newFrame = new InitialFrame(true, null, pastryOrder);
                setVisible(false);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
            }
            else if (pastryOfChoice.equals("Muffin")) {
                panel.remove(cookieFlavorsDropDown);
                panel.remove(cheesecakeSliceFlavorsDropDown);
                panel.remove(danishFlavorsDropDown);
                panel.remove(heatedBox);
                panel.remove(cancelButton);
                panel.repaint();
                panel.add(muffinFlavorsDropDown);
                panel.add(heatedLabel);
                panel.add(heatedBox);
                heatedBox.setSelected(false);
                panel.add(saveButton);
                panel.add(cancelButton);
                panel.revalidate();
                flavorOfChoice = (String) muffinFlavorsDropDown.getSelectedItem();
            }
            else if (pastryOfChoice.equals("Cookie")) {
                panel.remove(muffinFlavorsDropDown);
                panel.remove(cheesecakeSliceFlavorsDropDown);
                panel.remove(danishFlavorsDropDown);
                panel.remove(heatedBox);
                panel.remove(cancelButton);
                panel.repaint();
                panel.add(cookieFlavorsDropDown);
                panel.add(heatedLabel);
                panel.add(heatedBox);
                heatedBox.setSelected(false);
                panel.add(saveButton);
                panel.add(cancelButton);
                panel.revalidate();
                flavorOfChoice = (String) cookieFlavorsDropDown.getSelectedItem();
            }
            else if (pastryOfChoice.equals("Cheesecake slice")) {
                panel.remove(muffinFlavorsDropDown);
                panel.remove(cookieFlavorsDropDown);
                panel.remove(danishFlavorsDropDown);
                panel.remove(heatedBox);
                panel.remove(cancelButton);
                panel.repaint();
                panel.add(cheesecakeSliceFlavorsDropDown);
                panel.add(heatedLabel);
                panel.add(heatedBox);
                heatedBox.setSelected(false);
                panel.add(saveButton);
                panel.add(cancelButton);
                panel.revalidate();
                flavorOfChoice = (String) cheesecakeSliceFlavorsDropDown.getSelectedItem();
            }
            else if (pastryOfChoice.equals("Danish")) {
                panel.remove(muffinFlavorsDropDown);
                panel.remove(cookieFlavorsDropDown);
                panel.remove(cheesecakeSliceFlavorsDropDown);
                panel.remove(heatedBox);
                panel.remove(cancelButton);
                panel.repaint();
                panel.add(danishFlavorsDropDown);
                panel.add(heatedLabel);
                panel.add(heatedBox);
                heatedBox.setSelected(false);
                panel.add(saveButton);
                panel.add(cancelButton);
                panel.revalidate();
                flavorOfChoice = (String) danishFlavorsDropDown.getSelectedItem();
            }
        }
    }
}