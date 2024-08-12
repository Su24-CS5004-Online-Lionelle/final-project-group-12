package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.Enclosure;
import zoosys.model.EnclosureImpl;
import zoosys.model.EnclosureType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * The EnclosureManagementView class provides the graphical user interface (GUI)
 * for managing enclosures in the Enclosure Management System.
 */
public class EnclosureManagementView extends JFrame {
    private controller controller;
    private JTable table;
    private DefaultTableModel tableModel;

    /**
     * Constructs a new EnclosureManagementView instance.
     * @param controller the controller that manages interactions between the view and the model.
     */
    public EnclosureManagementView(controller controller) {
        this.controller = controller;
        setTitle("Enclosure Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initialize();
        populateTable();
        setVisible(true);
    }

    /**
     * Initializes the components in the Enclosure Management GUI.
     */
    private void initialize() {
        JPanel enclosurePanel = new JPanel();
        enclosurePanel.setLayout(new BoxLayout(enclosurePanel, BoxLayout.Y_AXIS));
        enclosurePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create buttons for adding, editing, and deleting enclosures
        JButton addEnclosureButton = new JButton("Add Enclosure");
        JButton editEnclosureButton = new JButton("Edit Enclosure");
        JButton deleteEnclosureButton = new JButton("Delete Enclosure");

        addEnclosureButton.addActionListener(e -> showAddEnclosureDialog());
        editEnclosureButton.addActionListener(e -> showEditEnclosureDialog());
        deleteEnclosureButton.addActionListener(e -> showDeleteEnclosureDialog());

        // Set up the table to display enclosure information
        tableModel = new DefaultTableModel(new Object[]{
                "Size", "Humidity", "Temperature", "Vegetation Coverage", "Zone Cleanliness", "Food in Trough", "Type"
        }, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        enclosurePanel.add(addEnclosureButton);
        enclosurePanel.add(editEnclosureButton);
        enclosurePanel.add(deleteEnclosureButton);
        enclosurePanel.add(scrollPane);

        add(enclosurePanel);
    }

    /**
     * Displays a dialog for adding a new enclosure.
     */
    private void showAddEnclosureDialog() {
        JDialog addEnclosureDialog = createEnclosureDialog("Add Enclosure", true, null);
        addEnclosureDialog.setVisible(true);
    }

    /**
     * Displays a dialog for editing an existing enclosure.
     */
    private void showEditEnclosureDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an enclosure to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Enclosure selectedEnclosure = getEnclosureFromTableRow(selectedRow);
        JDialog editEnclosureDialog = createEnclosureDialog("Edit Enclosure", false, selectedEnclosure);
        editEnclosureDialog.setVisible(true);
    }

    /**
     * Displays a dialog for deleting an existing enclosure.
     */
    private void showDeleteEnclosureDialog() {
        JDialog deleteEnclosureDialog = new JDialog(this, "Delete Enclosure", true);
        deleteEnclosureDialog.setSize(300, 150);
        deleteEnclosureDialog.setLayout(new GridLayout(3, 2));

        JLabel sizeLabel = new JLabel("Size:");
        JTextField sizeField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<EnclosureType> typeComboBox = new JComboBox<>(EnclosureType.values());
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");

        deleteButton.addActionListener(e -> {
            try {
                double size = Double.parseDouble(sizeField.getText());
                EnclosureType type = (EnclosureType) typeComboBox.getSelectedItem();
                boolean success = controller.removeEnclosure(type);
                if (success) {
                    populateTable();
                    deleteEnclosureDialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(deleteEnclosureDialog, "Enclosure not found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(deleteEnclosureDialog, "Invalid size. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> deleteEnclosureDialog.dispose());

        deleteEnclosureDialog.add(sizeLabel);
        deleteEnclosureDialog.add(sizeField);
        deleteEnclosureDialog.add(typeLabel);
        deleteEnclosureDialog.add(typeComboBox);
        deleteEnclosureDialog.add(deleteButton);
        deleteEnclosureDialog.add(cancelButton);

        deleteEnclosureDialog.setVisible(true);
    }

    /**
     * Creates a dialog for adding or editing an enclosure.
     * @param title the title of the dialog
     * @param isAddDialog true if this is an add dialog, false for an edit dialog
     * @param enclosure the enclosure to edit, or null for an add dialog
     * @return the created JDialog
     */
    private JDialog createEnclosureDialog(String title, boolean isAddDialog, Enclosure enclosure) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridLayout(8, 2));

        JLabel sizeLabel = new JLabel("Size:");
        JTextField sizeField = new JTextField();
        JLabel humidityLabel = new JLabel("Humidity:");
        JTextField humidityField = new JTextField();
        JLabel temperatureLabel = new JLabel("Temperature:");
        JTextField temperatureField = new JTextField();
        JLabel vegetationCoverageLabel = new JLabel("Vegetation Coverage:");
        JTextField vegetationCoverageField = new JTextField();
        JLabel zoneCleanlinessLabel = new JLabel("Zone Cleanliness:");
        JTextField zoneCleanlinessField = new JTextField();
        JLabel foodInTroughLabel = new JLabel("Food in Trough:");
        JTextField foodInTroughField = new JTextField();
        JLabel typeLabel = new JLabel("Type:");
        JComboBox<EnclosureType> typeComboBox = new JComboBox<>(EnclosureType.values());

        if (!isAddDialog && enclosure != null) {
            sizeField.setText(String.valueOf(enclosure.getSize()));
            humidityField.setText(String.valueOf(enclosure.getHumidity()));
            temperatureField.setText(String.valueOf(enclosure.getTemperature()));
            vegetationCoverageField.setText(String.valueOf(enclosure.getVegetationCoverage()));
            zoneCleanlinessField.setText(String.valueOf(enclosure.getZoneCleanliness()));
            foodInTroughField.setText(String.valueOf(enclosure.getFoodInTrough()));
            typeComboBox.setSelectedItem(enclosure.getEnclosureType());
        }

        JButton submitButton = new JButton(isAddDialog ? "Add" : "Update");
        JButton cancelButton = new JButton("Cancel");

        submitButton.addActionListener(e -> {
            try {
                double size = Double.parseDouble(sizeField.getText());
                double humidity = Double.parseDouble(humidityField.getText());
                double temperature = Double.parseDouble(temperatureField.getText());
                double vegetationCoverage = Double.parseDouble(vegetationCoverageField.getText());
                double zoneCleanliness = Double.parseDouble(zoneCleanlinessField.getText());
                double foodInTrough = Double.parseDouble(foodInTroughField.getText());
                EnclosureType type = (EnclosureType) typeComboBox.getSelectedItem();

                EnclosureImpl newEnclosure = new EnclosureImpl(size, humidity, temperature, vegetationCoverage, zoneCleanliness, foodInTrough, type);
                newEnclosure.setType(type);

                boolean success;
                if (isAddDialog) {
                    success = controller.addEnclosure(newEnclosure);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "Enclosure successfully added", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Enclosure already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    success = controller.updateEnclosure(enclosure.getEnclosureType(), newEnclosure);
                    if (success) {
                        JOptionPane.showMessageDialog(dialog, "Enclosure successfully updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(dialog, "Enclosure not found", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                if (success) {
                    populateTable();
                    dialog.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.add(sizeLabel);
        dialog.add(sizeField);
        dialog.add(humidityLabel);
        dialog.add(humidityField);
        dialog.add(temperatureLabel);
        dialog.add(temperatureField);
        dialog.add(vegetationCoverageLabel);
        dialog.add(vegetationCoverageField);
        dialog.add(zoneCleanlinessLabel);
        dialog.add(zoneCleanlinessField);
        dialog.add(foodInTroughLabel);
        dialog.add(foodInTroughField);
        dialog.add(typeLabel);
        dialog.add(typeComboBox);
        dialog.add(submitButton);
        dialog.add(cancelButton);

        return dialog;
    }

    /**
     * Retrieves the enclosure object from the selected table row.
     * @param row the selected row index
     * @return the Enclosure object from the specified row
     */
    private Enclosure getEnclosureFromTableRow(int row) {
        double size = (Double) tableModel.getValueAt(row, 0);
        double humidity = (Double) tableModel.getValueAt(row, 1);
        double temperature = (Double) tableModel.getValueAt(row, 2);
        double vegetationCoverage = (Double) tableModel.getValueAt(row, 3);
        double zoneCleanliness = (Double) tableModel.getValueAt(row, 4);
        double foodInTrough = (Double) tableModel.getValueAt(row, 5);
        EnclosureType type = (EnclosureType) tableModel.getValueAt(row, 6);

        return new EnclosureImpl(size, humidity, temperature, vegetationCoverage, zoneCleanliness, foodInTrough, type);
    }

    /**
     * Populates the table with data from the enclosure management system.
     */
    private void populateTable() {
        tableModel.setRowCount(0);
        List<Enclosure> enclosures = controller.getAllEnclosures();

        for (Enclosure enclosure : enclosures) {
            tableModel.addRow(new Object[]{
                    enclosure.getSize(),
                    enclosure.getHumidity(),
                    enclosure.getTemperature(),
                    enclosure.getVegetationCoverage(),
                    enclosure.getZoneCleanliness(),
                    enclosure.getFoodInTrough(),
                    enclosure.getEnclosureType()
            });
        }
    }
}
