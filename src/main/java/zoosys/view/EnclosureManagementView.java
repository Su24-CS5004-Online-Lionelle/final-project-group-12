package zoosys.view;

import zoosys.controller.Controller;
import zoosys.model.*;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnclosureManagementView extends JFrame {
    private Controller controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public EnclosureManagementView(Controller controller) {
        this.controller = controller;
        setTitle("Enclosure Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialize();
        populateTable(); // Populate table with enclosure data on initialization

        setVisible(true);
    }

    private void initialize() {
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

        tableModel = new DefaultTableModel(new Object[]{"ID", "Size", "Humidity", "Temperature", "Vegetation Coverage", "Zone Cleanliness", "Food in Trough"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        enclosurePanel.add(addEnclosureButton);
        enclosurePanel.add(editEnclosureButton);
        enclosurePanel.add(deleteEnclosureButton);
        enclosurePanel.add(scrollPane);

        add(enclosurePanel);
    }

    private void showAddEnclosureDialog() {
        JDialog addEnclosureDialog = new JDialog(this, "Add Enclosure", true);
        addEnclosureDialog.setSize(400, 400);
        addEnclosureDialog.setLayout(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
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

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(idField.getText());
                    double size = Double.parseDouble(sizeField.getText());
                    double humidity = Double.parseDouble(humidityField.getText());
                    double temperature = Double.parseDouble(temperatureField.getText());
                    double vegetationCoverage = Double.parseDouble(vegetationCoverageField.getText());
                    int zoneCleanliness = Integer.parseInt(zoneCleanlinessField.getText());
                    int foodInTrough = Integer.parseInt(foodInTroughField.getText());

                    Enclosure newEnclosure = new EnclosureImpl(id, size, humidity, temperature, vegetationCoverage, 
                        zoneCleanliness, foodInTrough);
                    controller.addEnclosure(newEnclosure);
                    populateTable();

                    addEnclosureDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addEnclosureDialog, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        addEnclosureDialog.add(idLabel);
        addEnclosureDialog.add(idField);
        addEnclosureDialog.add(sizeLabel);
        addEnclosureDialog.add(sizeField);
        addEnclosureDialog.add(humidityLabel);
        addEnclosureDialog.add(humidityField);
        addEnclosureDialog.add(temperatureLabel);
        addEnclosureDialog.add(temperatureField);
        addEnclosureDialog.add(vegetationCoverageLabel);
        addEnclosureDialog.add(vegetationCoverageField);
        addEnclosureDialog.add(zoneCleanlinessLabel);
        addEnclosureDialog.add(zoneCleanlinessField);
        addEnclosureDialog.add(foodInTroughLabel);
        addEnclosureDialog.add(foodInTroughField);
        addEnclosureDialog.add(submitButton);

        addEnclosureDialog.setVisible(true);
    }

    private void showEditEnclosureDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select an enclosure to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        Enclosure enclosure = controller.getEnclosureManagement().getEnclosureById(id);

        if (enclosure == null) {
            JOptionPane.showMessageDialog(this, "Enclosure not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog editEnclosureDialog = new JDialog(this, "Edit Enclosure", true);
        editEnclosureDialog.setSize(400, 400);
        editEnclosureDialog.setLayout(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(String.valueOf(enclosure.getId()));
        idField.setEditable(false);
        JLabel sizeLabel = new JLabel("Size:");
        JTextField sizeField = new JTextField(String.valueOf(enclosure.getSize()));
        JLabel humidityLabel = new JLabel("Humidity:");
        JTextField humidityField = new JTextField(String.valueOf(enclosure.getHumidity()));
        JLabel temperatureLabel = new JLabel("Temperature:");
        JTextField temperatureField = new JTextField(String.valueOf(enclosure.getTemperature()));
        JLabel vegetationCoverageLabel = new JLabel("Vegetation Coverage:");
        JTextField vegetationCoverageField = new JTextField(String.valueOf(enclosure.getVegetationCoverage()));
        JLabel zoneCleanlinessLabel = new JLabel("Zone Cleanliness:");
        JTextField zoneCleanlinessField = new JTextField(String.valueOf(enclosure.getZoneCleanliness()));
        JLabel foodInTroughLabel = new JLabel("Food in Trough:");
        JTextField foodInTroughField = new JTextField(String.valueOf(enclosure.getFoodInTrough()));

        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double size = Double.parseDouble(sizeField.getText());
                    double humidity = Double.parseDouble(humidityField.getText());
                    double temperature = Double.parseDouble(temperatureField.getText());
                    double vegetationCoverage = Double.parseDouble(vegetationCoverageField.getText());
                    int zoneCleanliness = Integer.parseInt(zoneCleanlinessField.getText());
                    int foodInTrough = Integer.parseInt(foodInTroughField.getText());

                    Enclosure updatedEnclosure = new EnclosureImpl(id, size, humidity, temperature, vegetationCoverage, 
                        zoneCleanliness, foodInTrough);
                    controller.updateEnclosure(updatedEnclosure);
                    populateTable();

                    editEnclosureDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(editEnclosureDialog, "Invalid input. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        editEnclosureDialog.add(idLabel);
        editEnclosureDialog.add(idField);
        editEnclosureDialog.add(sizeLabel);
        editEnclosureDialog.add(sizeField);
        editEnclosureDialog.add(humidityLabel);
        editEnclosureDialog.add(humidityField);
        editEnclosureDialog.add(temperatureLabel);
        editEnclosureDialog.add(temperatureField);
        editEnclosureDialog.add(vegetationCoverageLabel);
        editEnclosureDialog.add(vegetationCoverageField);
        editEnclosureDialog.add(zoneCleanlinessLabel);
        editEnclosureDialog.add(zoneCleanlinessField);
        editEnclosureDialog.add(foodInTroughLabel);
        editEnclosureDialog.add(foodInTroughField);
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
                try {
                    int id = Integer.parseInt(idField.getText());
                    controller.removeEnclosure(id);
                    populateTable();

                    deleteEnclosureDialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(deleteEnclosureDialog, "Invalid ID. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteEnclosureDialog.add(idLabel);
        deleteEnclosureDialog.add(idField);
        deleteEnclosureDialog.add(deleteButton);

        deleteEnclosureDialog.setVisible(true);
    }

    private void populateTable() {
        tableModel.setRowCount(0);
        controller.getEnclosureManagement().readCSV(); // Ensure the CSV data is loaded
        for (Enclosure enclosure : controller.getEnclosureManagement().getAllEnclosures()) {
            tableModel.addRow(new Object[]{
                enclosure.getId(),
                enclosure.getSize(),
                enclosure.getHumidity(),
                enclosure.getTemperature(),
                enclosure.getVegetationCoverage(),
                enclosure.getZoneCleanliness(),
                enclosure.getFoodInTrough()
            });
        }
    }
}
