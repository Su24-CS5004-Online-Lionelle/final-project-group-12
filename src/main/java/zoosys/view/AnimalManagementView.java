package zoosys.view;

import zoosys.controller.Controller;
import zoosys.model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * AnimalManagementView provides the user interface for managing animals,
 * including adding, editing, and deleting animals.
 */
public class AnimalManagementView extends JFrame {
    private final Controller controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public AnimalManagementView(Controller controller) {
        this.controller = controller;
        setTitle("Animal Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialize();
        populateTable(); // Populate table with animal data on initialization

        setVisible(true);
    }

    private void initialize() {
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

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Type", "Age", "Medical Record"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        animalPanel.add(addAnimalButton);
        animalPanel.add(editAnimalButton);
        animalPanel.add(deleteAnimalButton);
        animalPanel.add(scrollPane);

        add(animalPanel);
    }

    private void showAddAnimalDialog() {
        JDialog addAnimalDialog = new JDialog(this, "Add Animal", true);
        addAnimalDialog.setSize(400, 300);
        addAnimalDialog.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JTextField typeField = new JTextField();
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField();
        JLabel medicalRecordLabel = new JLabel("Medical Record:");
        JTextField medicalRecordField = new JTextField();

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = typeField.getText();
                int age = Integer.parseInt(ageField.getText());
                String medicalRecord = medicalRecordField.getText();

                controller.addAnimal(id, name, type, age, medicalRecord);
                populateTable();

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
        addAnimalDialog.add(medicalRecordLabel);
        addAnimalDialog.add(medicalRecordField);
        addAnimalDialog.add(submitButton);

        addAnimalDialog.setVisible(true);
    }

    private void showEditAnimalDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            IAnimal selectedAnimal = getAnimalAt(selectedRow);

            JDialog editAnimalDialog = new JDialog(this, "Edit Animal", true);
            editAnimalDialog.setSize(400, 300);
            editAnimalDialog.setLayout(new GridLayout(5, 2));

            JLabel idLabel = new JLabel("ID:");
            JTextField idField = new JTextField(String.valueOf(selectedAnimal.getAnimal_id()));
            JLabel nameLabel = new JLabel("Name:");
            JTextField nameField = new JTextField(selectedAnimal.getAnimal_name());
            JLabel typeLabel = new JLabel("Type:");
            JTextField typeField = new JTextField(selectedAnimal.getAnimal_type());
            JLabel ageLabel = new JLabel("Age:");
            JTextField ageField = new JTextField(String.valueOf(selectedAnimal.getAge()));
            JLabel medicalRecordLabel = new JLabel("Medical Record:");
            JTextField medicalRecordField = new JTextField(selectedAnimal.getMedical_record());

            JButton saveButton = new JButton("Save");

            saveButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int id = Integer.parseInt(idField.getText());
                    String name = nameField.getText();
                    String type = typeField.getText();
                    int age = Integer.parseInt(ageField.getText());
                    String medicalRecord = medicalRecordField.getText();

                    controller.updateAnimal(id, name, type, age, medicalRecord);
                    populateTable();

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
            editAnimalDialog.add(medicalRecordLabel);
            editAnimalDialog.add(medicalRecordField);
            editAnimalDialog.add(saveButton);

            editAnimalDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an animal to edit.");
        }
    }

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
                populateTable();

                deleteAnimalDialog.dispose();
            }
        });

        deleteAnimalDialog.add(idLabel);
        deleteAnimalDialog.add(idField);
        deleteAnimalDialog.add(deleteButton);

        deleteAnimalDialog.setVisible(true);
    }

    private void populateTable() {
        tableModel.setRowCount(0);
        List<IAnimal> animals = controller.getAllAnimals();
        for (IAnimal animal : animals) {
            tableModel.addRow(new Object[]{
                animal.getAnimal_id(),
                animal.getAnimal_name(),
                animal.getAnimal_type(),
                animal.getAge(),
                animal.getMedical_record()
            });
        }
    }

    private IAnimal getAnimalAt(int rowIndex) {
        int id = (int) tableModel.getValueAt(rowIndex, 0);
        return controller.getAnimal(id); // Updated method to get the animal by id
    }
}
