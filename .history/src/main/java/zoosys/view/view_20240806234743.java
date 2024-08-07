package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.*;
import zoosys.model.EmployeeManagement;
import zoosys.model.FeedingTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
                showAddAnimalDialog();
            }
        });

        editAnimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showEditAnimalDialog();
            }
        });

        deleteAnimalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteAnimalDialog();
            }
        });

        animalPanel.add(addAnimalButton);
        animalPanel.add(editAnimalButton);
        animalPanel.add(deleteAnimalButton);

        animalFrame.add(animalPanel);
        animalFrame.setVisible(true);
    }

    // Method to show the Add Animal dialog
    private void showAddAnimalDialog() {
        JDialog addAnimalDialog = new JDialog(this, "Add Animal", true);
        addAnimalDialog.setSize(300, 300);
        addAnimalDialog.setLayout(new GridLayout(7, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JTextField typeField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel feedingTimeLabel = new JLabel("Feeding Time (HH:MM):");
        JTextField feedingTimeField = new JTextField();
        JLabel medicalRecordLabel = new JLabel("Medical Record:");
        JTextField medicalRecordField = new JTextField();

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = typeField.getText();
                int age = Integer.parseInt(ageField.getText());
                FeedingTime feedingTime = new FeedingTime(Integer.parseInt(feedingTimeField.getText().split(":")[0]), Integer.parseInt(feedingTimeField.getText().split(":")[1]));
                ArrayList<FeedingTime> feedingTimes = new ArrayList<>();
                feedingTimes.add(feedingTime);
                String medicalRecord = medicalRecordField.getText();

                Animal animal = new Animal(id, name, type, age, feedingTimes, medicalRecord);
                controller.addAnimal(animal);

                addAnimalDialog.dispose();
            }
        });

        addAnimalDialog.add(idLabel);
        addAnimalDialog.add(idField);
        addAnimalDialog.add(nameLabel);
        addAnimalDialog.add(nameField);
        addAnimalDialog.add(typeLabel);
        addAnimalDialog.add(typeField);
        addAnimalDialog.add(ageLabel);
        addAnimalDialog.add(ageField);
        addAnimalDialog.add(feedingTimeLabel);
        addAnimalDialog.add(feedingTimeField);
        addAnimalDialog.add(medicalRecordLabel);
        addAnimalDialog.add(medicalRecordField);
        addAnimalDialog.add(submitButton);

        addAnimalDialog.setVisible(true);
    }

    // Method to show the Edit Animal dialog
    private void showEditAnimalDialog() {
        JDialog editAnimalDialog = new JDialog(this, "Edit Animal", true);
        editAnimalDialog.setSize(300, 300);
        editAnimalDialog.setLayout(new GridLayout(7, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JTextField typeField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel feedingTimeLabel = new JLabel("Feeding Time (HH:MM):");
        JTextField feedingTimeField = new JTextField();
        JLabel medicalRecordLabel = new JLabel("Medical Record:");
        JTextField medicalRecordField = new JTextField();

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = typeField.getText();
                int age = Integer.parseInt(ageField.getText());
                FeedingTime feedingTime = new FeedingTime(Integer.parseInt(feedingTimeField.getText().split(":")[0]), Integer.parseInt(feedingTimeField.getText().split(":")[1]));
                ArrayList<FeedingTime> feedingTimes = new ArrayList<>();
                feedingTimes.add(feedingTime);
                String medicalRecord = medicalRecordField.getText();

                Animal updatedAnimal = new Animal(id, name, type, age, feedingTimes, medicalRecord);
                controller.editAnimal(updatedAnimal);

                editAnimalDialog.dispose();
            }
        });

        editAnimalDialog.add(idLabel);
        editAnimalDialog.add(idField);
        editAnimalDialog.add(nameLabel);
        editAnimalDialog.add(nameField);
        editAnimalDialog.add(typeLabel);
        editAnimalDialog.add(typeField);
        editAnimalDialog.add(ageLabel);
        editAnimalDialog.add(ageField);
        editAnimalDialog.add(feedingTimeLabel);
        editAnimalDialog.add(feedingTimeField);
        editAnimalDialog.add(medicalRecordLabel);
        editAnimalDialog.add(medicalRecordField);
        editAnimalDialog.add(submitButton);

        editAnimalDialog.setVisible(true);
    }

    // Method to show the Delete Animal dialog
    private void showDeleteAnimalDialog() {
        JDialog deleteAnimalDialog = new JDialog(this, "Delete Animal", true);
        deleteAnimalDialog.setSize(300, 100);
        deleteAnimalDialog.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                controller.removeAnimal(id);

                deleteAnimalDialog.dispose();
            }
        });

        deleteAnimalDialog.add(idLabel);
        deleteAnimalDialog.add(idField);
        deleteAnimalDialog.add(deleteButton);

        deleteAnimalDialog.setVisible(true);
    }

    // Other methods (Enclosure, Employee, Visitor management) follow the same pattern...

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
