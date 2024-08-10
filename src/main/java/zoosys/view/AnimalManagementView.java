package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.Animal;
import zoosys.model.FeedingTime;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AnimalManagementView extends JFrame {
    private controller controller;
    private int enclosureId;

    // Constructor to initialize the Animal Management window
    public AnimalManagementView(controller controller, int enclosureId) {
        this.controller = controller;
        this.enclosureId = enclosureId;
        setTitle("Animal Management");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialize();

        setVisible(true);
    }

    public AnimalManagementView(zoosys.controller.controller controller2) {
        //TODO Auto-generated constructor stub
    }

    // Method to set up the initial layout and components
    private void initialize() {
        JPanel animalPanel = new JPanel();
        animalPanel.setLayout(new BoxLayout(animalPanel, BoxLayout.Y_AXIS));
        animalPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addAnimalButton = new JButton("Add Animal");
        JButton editAnimalButton = new JButton("Edit Animal");
        JButton deleteAnimalButton = new JButton("Delete Animal");

        // Add action listeners to the buttons
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

        // Add buttons to the panel
        animalPanel.add(addAnimalButton);
        animalPanel.add(editAnimalButton);
        animalPanel.add(deleteAnimalButton);

        add(animalPanel);
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

        // Add action listener to the submit button
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
                controller.addAnimal(enclosureId, animal);

                addAnimalDialog.dispose();
            }
        });

        // Add components to the dialog
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

        // Add action listener to the submit button
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
                controller.editAnimal(enclosureId, updatedAnimal);

                editAnimalDialog.dispose();
            }
        });

        // Add components to the dialog
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

        // Add action listener to the delete button
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                controller.removeAnimal(enclosureId, id);

                deleteAnimalDialog.dispose();
            }
        });

        // Add components to the dialog
        deleteAnimalDialog.add(idLabel);
        deleteAnimalDialog.add(idField);
        deleteAnimalDialog.add(deleteButton);

        deleteAnimalDialog.setVisible(true);
    }
}
