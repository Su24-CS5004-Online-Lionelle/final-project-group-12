package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.EnclosureType;
import zoosys.model.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class view extends JFrame {
    private JPanel mainPanel;
    private controller controller;

    // Constructor to initialize the main layout
    public view(controller controller) {
        this.controller = controller;
        setTitle("G12 Zoo Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        initialize();

        add(mainPanel);
    }

    // Method to set up the initial layout and components
    private void initialize() {
        // Create a JPanel for the side menu buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(new Color(200, 221, 242));

        // Create buttons for each management section
        JButton animalButton = new JButton("ANIMAL Management");
        JButton enclosureButton = new JButton("ENCLOSURE Management");
        JButton employeeButton = new JButton("EMPLOYEES Management");
        JButton visitorsButton = new JButton("VISITORS Information");
        JButton helpButton = new JButton("HELP");
        JButton exitButton = new JButton("EXIT");

        // Add buttons to the JPanel
        buttonPanel.add(animalButton);
        buttonPanel.add(enclosureButton);
        buttonPanel.add(employeeButton);
        buttonPanel.add(visitorsButton);
        buttonPanel.add(helpButton);
        buttonPanel.add(exitButton);

        // Create a JPanel for the display area
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(new Color(168, 218, 220));
        displayPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // Add the button panel to the left and the display panel to the center of the layout
        mainPanel.add(buttonPanel, BorderLayout.WEST);
        mainPanel.add(displayPanel, BorderLayout.CENTER);

        // Set action events for each button
        animalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Animal Management button clicked");
                openAnimalManagementWindow();
            }
        });

        enclosureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Enclosure Management button clicked");
                openEnclosureManagementWindow();
            }
        });

        employeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Employee Management button clicked");
                openEmployeeManagementWindow();
            }
        });

        visitorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Visitors Information button clicked");
                visitorsInformation();
            }
        });

        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Help button clicked");
                showHelpDialog();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    // Method to open the Animal Management window
    private void openAnimalManagementWindow() {
        new AnimalManagementView(controller);
    }

    // Method to open the Enclosure Management window
    private void openEnclosureManagementWindow() {
        JFrame enclosureFrame = new JFrame("Enclosure Management");
        enclosureFrame.setSize(400, 300);

        JPanel enclosurePanel = new JPanel();
        enclosurePanel.setLayout(new BoxLayout(enclosurePanel, BoxLayout.Y_AXIS));
        enclosurePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addEnclosureButton = new JButton("Add Enclosure");
        JButton editEnclosureButton = new JButton("Edit Enclosure");
        JButton deleteEnclosureButton = new JButton("Delete Enclosure");

        addEnclosureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddEnclosureDialog();
            }
        });

        editEnclosureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditEnclosureDialog();
            }
        });

        deleteEnclosureButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteEnclosureDialog();
            }
        });

        enclosurePanel.add(addEnclosureButton);
        enclosurePanel.add(editEnclosureButton);
        enclosurePanel.add(deleteEnclosureButton);

        enclosureFrame.add(enclosurePanel);
        enclosureFrame.setVisible(true);
    }

    // Methods to show the Add, Edit, and Delete Enclosure dialogs
    private void showAddEnclosureDialog() {
        JDialog addEnclosureDialog = new JDialog(this, "Add Enclosure", true);
        addEnclosureDialog.setSize(300, 300);
        addEnclosureDialog.setLayout(new GridLayout(7, 2));

        JLabel sizeLabel = new JLabel("Size:");
        JTextField sizeField = new JTextField();
        JLabel humidityLabel = new JLabel("Humidity:");
        JTextField humidityField = new JTextField();
        JLabel temperatureLabel = new JLabel("Temperature:");
        JTextField temperatureField = new JTextField();
        JLabel vegetationLabel = new JLabel("Vegetation Coverage:");
        JTextField vegetationField = new JTextField();
        JLabel cleanlinessLabel = new JLabel("Cleanliness:");
        JTextField cleanlinessField = new JTextField();
        JLabel foodLabel = new JLabel("Food in Trough:");
        JTextField foodField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<EnclosureType> typeComboBox = new JComboBox<>(EnclosureType.values());

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double size = Double.parseDouble(sizeField.getText());
                double humidity = Double.parseDouble(humidityField.getText());
                double temperature = Double.parseDouble(temperatureField.getText());
                double vegetationCoverage = Double.parseDouble(vegetationField.getText());
                int cleanliness = Integer.parseInt(cleanlinessField.getText());
                int food = Integer.parseInt(foodField.getText());
                EnclosureType type = (EnclosureType) typeComboBox.getSelectedItem();

                controller.setEnclosureSize(1, size);
                controller.setHumidity(1,humidity);
                controller.setTemperature(1,temperature);
                controller.setVegetationCoverage(1,vegetationCoverage);
                controller.setZoneCleanliness(1,cleanliness);
                controller.setFoodInTrough(1,food);
                controller.setEnclosureType(1,type);

                addEnclosureDialog.dispose();
            }
        });

        addEnclosureDialog.add(sizeLabel);
        addEnclosureDialog.add(sizeField);
        addEnclosureDialog.add(humidityLabel);
        addEnclosureDialog.add(humidityField);
        addEnclosureDialog.add(temperatureLabel);
        addEnclosureDialog.add(temperatureField);
        addEnclosureDialog.add(vegetationLabel);
        addEnclosureDialog.add(vegetationField);
        addEnclosureDialog.add(cleanlinessLabel);
        addEnclosureDialog.add(cleanlinessField);
        addEnclosureDialog.add(foodLabel);
        addEnclosureDialog.add(foodField);
        addEnclosureDialog.add(typeLabel);
        addEnclosureDialog.add(typeComboBox);
        addEnclosureDialog.add(submitButton);

        addEnclosureDialog.setVisible(true);
    }

    private void showEditEnclosureDialog() {
        JDialog editEnclosureDialog = new JDialog(this, "Edit Enclosure", true);
        editEnclosureDialog.setSize(300, 300);
        editEnclosureDialog.setLayout(new GridLayout(7, 2));

        JLabel sizeLabel = new JLabel("Size:");
        JTextField sizeField = new JTextField();
        JLabel humidityLabel = new JLabel("Humidity:");
        JTextField humidityField = new JTextField();
        JLabel temperatureLabel = new JLabel("Temperature:");
        JTextField temperatureField = new JTextField();
        JLabel vegetationLabel = new JLabel("Vegetation Coverage:");
        JTextField vegetationField = new JTextField();
        JLabel cleanlinessLabel = new JLabel("Cleanliness:");
        JTextField cleanlinessField = new JTextField();
        JLabel foodLabel = new JLabel("Food in Trough:");
        JTextField foodField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<EnclosureType> typeComboBox = new JComboBox<>(EnclosureType.values());

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double size = Double.parseDouble(sizeField.getText());
                double humidity = Double.parseDouble(humidityField.getText());
                double temperature = Double.parseDouble(temperatureField.getText());
                double vegetationCoverage = Double.parseDouble(vegetationField.getText());
                int cleanliness = Integer.parseInt(cleanlinessField.getText());
                int food = Integer.parseInt(foodField.getText());
                EnclosureType type = (EnclosureType) typeComboBox.getSelectedItem();

                controller.setEnclosureSize(1,size);
                controller.setHumidity(1,humidity);
                controller.setTemperature(1,temperature);
                controller.setVegetationCoverage(1,vegetationCoverage);
                controller.setZoneCleanliness(1,cleanliness);
                controller.setFoodInTrough(1,food);
                controller.setEnclosureType(1,type);

                editEnclosureDialog.dispose();
            }
        });

        editEnclosureDialog.add(sizeLabel);
        editEnclosureDialog.add(sizeField);
        editEnclosureDialog.add(humidityLabel);
        editEnclosureDialog.add(humidityField);
        editEnclosureDialog.add(temperatureLabel);
        editEnclosureDialog.add(temperatureField);
        editEnclosureDialog.add(vegetationLabel);
        editEnclosureDialog.add(vegetationField);
        editEnclosureDialog.add(cleanlinessLabel);
        editEnclosureDialog.add(cleanlinessField);
        editEnclosureDialog.add(foodLabel);
        editEnclosureDialog.add(foodField);
        editEnclosureDialog.add(typeLabel);
        editEnclosureDialog.add(typeComboBox);
        editEnclosureDialog.add(submitButton);

        editEnclosureDialog.setVisible(true);
    }

    private void showDeleteEnclosureDialog() {
        JDialog deleteEnclosureDialog = new JDialog(this, "Delete Enclosure", true);
        deleteEnclosureDialog.setSize(300, 100);
        deleteEnclosureDialog.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                // Assuming there's a method in controller to delete an enclosure by ID
                // controller.removeEnclosure(id);

                deleteEnclosureDialog.dispose();
            }
        });

        deleteEnclosureDialog.add(idLabel);
        deleteEnclosureDialog.add(idField);
        deleteEnclosureDialog.add(deleteButton);

        deleteEnclosureDialog.setVisible(true);
    }

    // Method to open the Employee Management window
    private void openEmployeeManagementWindow() {
        new EmployeeManagementView(controller);
    }

    // Method to open the Visitors Information window
    public void visitorsInformation() {
        Visitor visitor = new Visitor();
        new VisitorView(visitor);
    }

    // Method to show the Help dialog
    private void showHelpDialog() {
        JFrame helpFrame = new JFrame("Help");
        helpFrame.setSize(400, 300);

        JPanel helpPanel = new JPanel();
        helpPanel.setLayout(new BoxLayout(helpPanel, BoxLayout.Y_AXIS));
        helpPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton faqButton = new JButton("FAQ");
        JButton contactSupportButton = new JButton("Contact Support");

        faqButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showFAQDialog();
            }
        });

        contactSupportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showContactSupportDialog();
            }
        });

        helpPanel.add(faqButton);
        helpPanel.add(contactSupportButton);

        helpFrame.add(helpPanel);
        helpFrame.setVisible(true);
    }

    // Method to show the FAQ dialog
    private void showFAQDialog() {
        String faqText = "Frequently Asked Questions:\n\n"
                + "1. What are the zoo's opening hours?\n"
                + "   Our zoo is open from 9:00 AM to 5:00 PM every day.\n\n"
                + "2. How much is the entrance fee?\n"
                + "   The entrance fee is $20 for adults, $15 for teens, $10 for elderly, and $5 for minors.\n\n"
                + "3. Can I bring my own food?\n"
                + "   Yes, you can bring your own food, but we also have a variety of food options available inside the zoo.\n\n"
                + "4. Is there a discount for group visits?\n"
                + "   Yes, we offer discounts for groups of 10 or more. Please contact support for more details.\n\n"
                + "5. Are pets allowed in the zoo?\n"
                + "   No, pets are not allowed inside the zoo to ensure the safety of our animals and visitors.";

        JOptionPane.showMessageDialog(this, faqText, "FAQ", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to show the Contact Support dialog
    private void showContactSupportDialog() {
        JOptionPane.showMessageDialog(this, "Contact Support:\nEmail: support@zoo.com\nPhone: 123-456-7890", "Contact Support", JOptionPane.INFORMATION_MESSAGE);
    }
}
