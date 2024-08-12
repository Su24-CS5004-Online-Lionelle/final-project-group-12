package zoosys.view;

import zoosys.model.AnimalCategory;
import zoosys.model.AnimalNew;
import zoosys.model.FoodType;
import zoosys.model.Sex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

/**
 * Yangcheng Luo
 * The current code will have the controller methods in it.Bowen is responsible for the controller part he can take it out and put it under the controller.
 * It is easier for me to test my gui this way.
 * GUI for managing animal info in the zoo app.
 */
public class AnimalView extends JFrame {
    private AnimalNew animalNew;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextArea infoTextArea;
    private JTextField nameTextField, ageTextField, removeTextField, editNameTextField, editNewNameTextField, editAgeTextField;
    private JComboBox<AnimalCategory> categoryComboBox, editCategoryComboBox;
    private JComboBox<FoodType> foodTypeComboBox, editFoodTypeComboBox;
    private JComboBox<Sex> sexComboBox, editSexComboBox;
    private JCheckBox medicalAttentionCheckBox, editMedicalAttentionCheckBox;

    /**
     * Constructor for the AnimalView class.
     * Initializes the GUI components and sets up the layout.
     *
     * @param animalNew The AnimalNew object to interact with the animal data model.
     */
    public AnimalView(AnimalNew animalNew) {
        this.animalNew = animalNew;

        setTitle("Animal Information");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Category", "Name", "Age", "Food Type", "Medical Attention Needed", "Sex"}, 0);

        // Create JTable with the table model
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Information area for statistics
        infoTextArea = new JTextArea(10, 50);
        infoTextArea.setEditable(false);
        add(new JScrollPane(infoTextArea), BorderLayout.SOUTH);

        // Form for adding animals
        JPanel formPanel = new JPanel(new GridLayout(8, 2));

        formPanel.add(new JLabel("Name:"));
        nameTextField = new JTextField();
        formPanel.add(nameTextField);

        formPanel.add(new JLabel("Age:"));
        ageTextField = new JTextField();
        formPanel.add(ageTextField);

        formPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(AnimalCategory.values());
        formPanel.add(categoryComboBox);

        formPanel.add(new JLabel("Food Type:"));
        foodTypeComboBox = new JComboBox<>(FoodType.values());
        formPanel.add(foodTypeComboBox);

        formPanel.add(new JLabel("Sex:"));
        sexComboBox = new JComboBox<>(Sex.values());
        formPanel.add(sexComboBox);

        formPanel.add(new JLabel("Medical Attention Needed:"));
        medicalAttentionCheckBox = new JCheckBox();
        formPanel.add(medicalAttentionCheckBox);

        JButton addButton = new JButton("Add Animal");
        formPanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText().trim();

                // Validate the animal name is unique
                if (isNameDuplicate(name)) {
                    JOptionPane.showMessageDialog(null, "Animal name already exists! Please enter a different name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age = Integer.parseInt(ageTextField.getText().trim());
                AnimalCategory category = (AnimalCategory) categoryComboBox.getSelectedItem();
                FoodType foodType = (FoodType) foodTypeComboBox.getSelectedItem();
                Sex sex = (Sex) sexComboBox.getSelectedItem();
                boolean medicalAttention = medicalAttentionCheckBox.isSelected();

                animalNew.addAnimal(category, name, age, foodType, medicalAttention, sex);
                refreshTable();
                displayStatistics();
            }
        });

        // Form for removing animals
        formPanel.add(new JLabel("Remove Animal by Name:"));
        removeTextField = new JTextField();
        formPanel.add(removeTextField);

        JButton removeButton = new JButton("Remove Animal");
        formPanel.add(removeButton);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = removeTextField.getText().trim();
                animalNew.removeAnimal(name);
                refreshTable();
                displayStatistics();
            }
        });

        // Form for editing animals
        formPanel.add(new JLabel("Edit Existing Animal by Name:"));
        editNameTextField = new JTextField();
        formPanel.add(editNameTextField);

        formPanel.add(new JLabel("New Name:"));
        editNewNameTextField = new JTextField();
        formPanel.add(editNewNameTextField);

        formPanel.add(new JLabel("New Age:"));
        editAgeTextField = new JTextField();
        formPanel.add(editAgeTextField);

        formPanel.add(new JLabel("New Category:"));
        editCategoryComboBox = new JComboBox<>(AnimalCategory.values());
        formPanel.add(editCategoryComboBox);

        formPanel.add(new JLabel("New Food Type:"));
        editFoodTypeComboBox = new JComboBox<>(FoodType.values());
        formPanel.add(editFoodTypeComboBox);

        formPanel.add(new JLabel("New Sex:"));
        editSexComboBox = new JComboBox<>(Sex.values());
        formPanel.add(editSexComboBox);

        formPanel.add(new JLabel("New Medical Attention Needed:"));
        editMedicalAttentionCheckBox = new JCheckBox();
        formPanel.add(editMedicalAttentionCheckBox);

        JButton editButton = new JButton("Edit Animal");
        formPanel.add(editButton);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String existingName = editNameTextField.getText().trim();
                String newName = editNewNameTextField.getText().trim();

                if (!existingName.equalsIgnoreCase(newName) && isNameDuplicate(newName)) {
                    JOptionPane.showMessageDialog(null, "Animal name already exists! Please enter a different name.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int age = Integer.parseInt(editAgeTextField.getText().trim());
                AnimalCategory category = (AnimalCategory) editCategoryComboBox.getSelectedItem();
                FoodType foodType = (FoodType) editFoodTypeComboBox.getSelectedItem();
                Sex sex = (Sex) editSexComboBox.getSelectedItem();
                boolean medicalAttention = editMedicalAttentionCheckBox.isSelected();

                animalNew.updateAnimal(existingName, category, newName, age, foodType, medicalAttention, sex);
                refreshTable();
                displayStatistics();
            }
        });

        add(formPanel, BorderLayout.NORTH);

        // Load data and display statistics
        loadAndDisplayData();

        setVisible(true);
    }

    /**
     * Loads animal data and refreshes the table and statistics display.
     */
    private void loadAndDisplayData() {
        animalNew.loadAnimalData();  // Load data first
        refreshTable();  // Then refresh the table
        displayStatistics();  // Finally, display statistics
    }

    /**
     * Checks if an animal name already exists in the system.
     *
     * @param name The name to check for duplicates.
     * @return True if the name exists, false otherwise.
     */
    private boolean isNameDuplicate(String name) {
        List<List<String>> animals = animalNew.getAllAnimals();
        for (List<String> animal : animals) {
            if (animal.get(1).equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Refreshes the JTable with the latest animal data.
     */
    private void refreshTable() {
        tableModel.setRowCount(0); // Clear existing rows
        List<List<String>> animals = animalNew.getAllAnimals();

        // Sort animals by category and then by name
        animals.sort((a, b) -> {
            int categoryComparison = a.get(0).compareTo(b.get(0));
            if (categoryComparison != 0) {
                return categoryComparison;
            }
            return a.get(1).compareTo(b.get(1));
        });

        // Add rows to the table
        for (List<String> animal : animals) {
            tableModel.addRow(new Object[]{
                    animal.get(0), // Category
                    animal.get(1), // Name
                    Integer.parseInt(animal.get(2)), // Age
                    animal.get(3), // Food Type
                    animal.get(4), // Medical Attention Needed
                    animal.get(5)  // Sex
            });
        }
    }

    /**
     * Displays various statistics about the animals, such as total count and breakdown by category.
     */
    private void displayStatistics() {
        int totalAnimals = animalNew.getTotalAnimals();

        Map<AnimalCategory, Integer> animalsByCategory = animalNew.getAnimalsCountByCategory();
        Map<FoodType, Integer> foodCount = animalNew.getTotalFoodNeeded();
        List<String> animalsNeedingAttention = animalNew.getAnimalsNeedingMedicalAttention();

        // Display information
        infoTextArea.setText("Total number of animals: " + totalAnimals + "\n");

        for (Map.Entry<AnimalCategory, Integer> entry : animalsByCategory.entrySet()) {
            infoTextArea.append("Number of " + entry.getKey().toString().toLowerCase() + "s: " + entry.getValue() + "\n");
        }

        for (Map.Entry<FoodType, Integer> entry : foodCount.entrySet()) {
            infoTextArea.append("Total " + entry.getKey().toString().toLowerCase() + " needed: " + entry.getValue() + "\n");
        }

        infoTextArea.append("Animals needing medical attention: " + animalsNeedingAttention.size() + "\n");
        if (!animalsNeedingAttention.isEmpty()) {
            infoTextArea.append("Names: " + String.join(", ", animalsNeedingAttention) + "\n");
        }
    }
}
