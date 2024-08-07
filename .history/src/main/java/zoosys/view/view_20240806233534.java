package zoosys.view;

import zoosys.controller.controller;

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
                openVisitorsInformationWindow();
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
        JFrame animalFrame = new JFrame("Animal Management");
        animalFrame.setSize(400, 300);

        JPanel animalPanel = new JPanel();
        animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));
        animalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addAnimalButton = new JButton("Add Animal");
        JButton editAnimalButton = new JButton("Edit Animal");
        JButton deleteAnimalButton = new JButton("Delete Animal");

        addAnimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Animal button clicked");
                // Call the appropriate method on the controller
                // Example: controller.addAnimal(new Animal(...));
            }
        });

        animalPanel.add(addAnimalButton);
        animalPanel.add(editAnimalButton);
        animalPanel.add(deleteAnimalButton);

        animalFrame.add(animalPanel);
        animalFrame.setVisible(true);
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
                System.out.println("Add Enclosure button clicked");
                // Call the appropriate method on the controller
                // Example: controller.addEnclosure(new Enclosure(...));
            }
        });

        enclosurePanel.add(addEnclosureButton);
        enclosurePanel.add(editEnclosureButton);
        enclosurePanel.add(deleteEnclosureButton);

        enclosureFrame.add(enclosurePanel);
        enclosureFrame.setVisible(true);
    }

    // Method to open the Employee Management window
    private void openEmployeeManagementWindow() {
        JFrame employeeFrame = new JFrame("Employee Management");
        employeeFrame.setSize(400, 300);

        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));
        employeePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton editEmployeeButton = new JButton("Edit Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");

        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Employee button clicked");
                // Call the appropriate method on the controller
                // Example: controller.addEmployee(new Employee(...));
            }
        });

        employeePanel.add(addEmployeeButton);
        employeePanel.add(editEmployeeButton);
        employeePanel.add(deleteEmployeeButton);

        employeeFrame.add(employeePanel);
        employeeFrame.setVisible(true);
    }

    // Method to open the Visitors Information window
    private void openVisitorsInformationWindow() {
        JFrame visitorsFrame = new JFrame("Visitors Information");
        visitorsFrame.setSize(400, 300);

        JPanel visitorsPanel = new JPanel();
        visitorsPanel.setLayout(new BoxLayout(visitorsPanel, BoxLayout.Y_AXIS));
        visitorsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addVisitorButton = new JButton("Add Visitor");
        JButton editVisitorButton = new JButton("Edit Visitor");
        JButton deleteVisitorButton = new JButton("Delete Visitor");

        addVisitorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Add Visitor button clicked");
                // Call the appropriate method on the controller
                // Example: controller.addVisitor(new Visitor(...));
            }
        });

        visitorsPanel.add(addVisitorButton);
        visitorsPanel.add(editVisitorButton);
        visitorsPanel.add(deleteVisitorButton);

        visitorsFrame.add(visitorsPanel);
        visitorsFrame.setVisible(true);
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
