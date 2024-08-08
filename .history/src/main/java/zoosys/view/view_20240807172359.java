package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

public class view extends JFrame {
    private JPanel mainPanel;
    private controller controller;
    private IVisitor visitor;

    // Constructor to initialize the main layout
    public view(controller controller) {
        this.controller = controller;
        this.visitor = visitor;
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

                controller.setEnclosureSize(size);
                controller.setHumidity(humidity);
                controller.setTemperature(temperature);
                controller.setVegetationCoverage(vegetationCoverage);
                controller.setZoneCleanliness(cleanliness);
                controller.setFoodInTrough(food);
                controller.setEnclosureType(type);

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

                controller.setEnclosureSize(size);
                controller.setHumidity(humidity);
                controller.setTemperature(temperature);
                controller.setVegetationCoverage(vegetationCoverage);
                controller.setZoneCleanliness(cleanliness);
                controller.setFoodInTrough(food);
                controller.setEnclosureType(type);

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
        JFrame employeeFrame = new JFrame("Employee Management");
        employeeFrame.setSize(400, 300);

        JPanel employeePanel = new JPanel();
        employeePanel.setLayout(new BoxLayout(employeePanel, BoxLayout.Y_AXIS));
        employeePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addEmployeeButton = new JButton("Add Employee");
        JButton updateEmployeeButton = new JButton("Update Employee");
        JButton deleteEmployeeButton = new JButton("Delete Employee");

        addEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showAddEmployeeDialog();
            }
        });

        updateEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showupdateEmployeeDialog();
            }
        });

        deleteEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showDeleteEmployeeDialog();
            }
        });

        employeePanel.add(addEmployeeButton);
        employeePanel.add(updateEmployeeButton);
        employeePanel.add(deleteEmployeeButton);

        employeeFrame.add(employeePanel);
        employeeFrame.setVisible(true);
    }

    // Methods to show the Add, Edit, and Delete Employee dialogs
    private void showAddEmployeeDialog() {
        JDialog addEmployeeDialog = new JDialog(this, "Add Employee", true);
        addEmployeeDialog.setSize(300, 300);
        addEmployeeDialog.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();
        JLabel shiftLabel = new JLabel("Shift:");
        JTextField shiftField = new JTextField();
        JLabel responsibilitiesLabel = new JLabel("Responsibilities:");
        JTextField responsibilitiesField = new JTextField();

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String role = roleField.getText();
                String shift = shiftField.getText();
                String responsibilities = responsibilitiesField.getText();
                controller.addEmployee(name, role, shift, responsibilities);

                addEmployeeDialog.dispose();
            }
        });

        addEmployeeDialog.add(nameLabel);
        addEmployeeDialog.add(nameField);
        addEmployeeDialog.add(roleLabel);
        addEmployeeDialog.add(roleField);
        addEmployeeDialog.add(shiftLabel);
        addEmployeeDialog.add(shiftField);
        addEmployeeDialog.add(responsibilitiesLabel);
        addEmployeeDialog.add(responsibilitiesField);
        addEmployeeDialog.add(submitButton);

        addEmployeeDialog.setVisible(true);
    }

    private void showUpdateEmployeeDialog() {
        JDialog updateEmployeeDialog = new JDialog(this, "update Employee", true);
        updateEmployeeDialog.setSize(300, 300);
        updateEmployeeDialog.setLayout(new GridLayout(5, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel roleLabel = new JLabel("Role:");
        JTextField roleField = new JTextField();
        JLabel shiftLabel = new JLabel("Shift:");
        JTextField shiftField = new JTextField();
        JLabel responsibilitiesLabel = new JLabel("Responsibilities:");
        JTextField responsibilitiesField = new JTextField();

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String role = roleField.getText();
                String shift = shiftField.getText();
                String responsibilities = responsibilitiesField.getText();

                EmployeeImpl updateEmployee = new EmployeeImpl(name, role);
                updateEmployee.setShift(shift);
                updateEmployee.addResponsibility(responsibilities);

                controller.updateEmployee(name, updateEmployee);

                updateEmployeeDialog.dispose();
            }
        });

        updateEmployeeDialog.add(nameLabel);
        updateEmployeeDialog.add(nameField);
        updateEmployeeDialog.add(roleLabel);
        updateEmployeeDialog.add(roleField);
        updateEmployeeDialog.add(shiftLabel);
        updateEmployeeDialog.add(shiftField);
        updateEmployeeDialog.add(responsibilitiesLabel);
        updateEmployeeDialog.add(responsibilitiesField);
        updateEmployeeDialog.add(submitButton);

        updateEmployeeDialog.setVisible(true);
    }

    private void showDeleteEmployeeDialog() {
        JDialog deleteEmployeeDialog = new JDialog(this, "Delete Employee", true);
        deleteEmployeeDialog.setSize(300, 100);
        deleteEmployeeDialog.setLayout(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                controller.removeEmployee(name);

                deleteEmployeeDialog.dispose();
            }
        });

        deleteEmployeeDialog.add(nameLabel);
        deleteEmployeeDialog.add(nameField);
        deleteEmployeeDialog.add(deleteButton);

        deleteEmployeeDialog.setVisible(true);
    }

    // Method to open the Visitors Information window
    private void visitorsInformation() {
        JFrame visitorsFrame = new JFrame("Visitors Information");
        visitorsFrame.setSize(600, 400);

        JPanel visitorsPanel = new JPanel();
        visitorsPanel.setLayout(new BorderLayout());

        // Collect unique dates from the visits
        Set<String> availableDates = visitor.getVisits().stream()
                .map(Visit::getDate)
                .collect(Collectors.toSet());

        // Create JComboBox with the available dates
        JComboBox<String> dateComboBox = new JComboBox<>(availableDates.toArray(new String[0]));
        JTextArea visitorDetailsTextArea = new JTextArea(15, 50);
        visitorDetailsTextArea.setEditable(false);

        JPanel summaryPanel = new JPanel();
        summaryPanel.setLayout(new BoxLayout(summaryPanel, BoxLayout.Y_AXIS));
        JLabel visitsCountLabel = new JLabel();
        JLabel peakHourLabel = new JLabel();
        JLabel averageAnimalFeedbackLabel = new JLabel();
        JLabel averageCleanlinessFeedbackLabel = new JLabel();
        JLabel averagePricingFeedbackLabel = new JLabel();
        JLabel revenueLabel = new JLabel();

        // Add ActionListener to dateComboBox
        dateComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDate = (String) dateComboBox.getSelectedItem();
                if (selectedDate != null && !selectedDate.isEmpty()) {
                    // Build the details string directly without using a list
                    StringBuilder details = new StringBuilder();
                    for (Visit visit : visitor.getVisits()) {
                        if (visit.getDate().equals(selectedDate)) {
                            details.append(visit.toString()).append("\n");
                        }
                    }
                    visitorDetailsTextArea.setText(details.toString());

                    // Update summary labels
                    visitsCountLabel.setText("Total Visits: " + visitor.getVisitsCountByDate(selectedDate));
                    peakHourLabel.setText("Peak Hour: " + visitor.getPeakHourByDate(selectedDate));
                    averageAnimalFeedbackLabel.setText("Average Animal Feedback: " + visitor.getAverageAnimalFeedbackByDate(selectedDate));
                    averageCleanlinessFeedbackLabel.setText("Average Cleanliness Feedback: " + visitor.getAverageCleanlinessFeedbackByDate(selectedDate));
                    averagePricingFeedbackLabel.setText("Average Pricing Feedback: " + visitor.getAveragePricingFeedbackByDate(selectedDate));
                    revenueLabel.setText("Total Revenue: " + visitor.getRevenueByDate(selectedDate));
                }
            }
        });

        // Add components to summaryPanel
        summaryPanel.add(visitsCountLabel);
        summaryPanel.add(peakHourLabel);
        summaryPanel.add(averageAnimalFeedbackLabel);
        summaryPanel.add(averageCleanlinessFeedbackLabel);
        summaryPanel.add(averagePricingFeedbackLabel);
        summaryPanel.add(revenueLabel);

        // Add components to visitorsPanel
        visitorsPanel.add(dateComboBox, BorderLayout.NORTH);
        visitorsPanel.add(new JScrollPane(visitorDetailsTextArea), BorderLayout.CENTER);
        visitorsPanel.add(summaryPanel, BorderLayout.SOUTH);

        // Add visitorsPanel to visitorsFrame and make it visible
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
