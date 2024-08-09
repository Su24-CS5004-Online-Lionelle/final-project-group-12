package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.Enclosure;
import zoosys.model.EnclosureType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnclosureManagementView extends JFrame {
    private controller controller;
    private JTable table;
    private DefaultTableModel tableModel;

    public EnclosureManagementView(controller controller) {
        this.controller = controller;
        setTitle("Enclosure Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initialize();
        populateTable();

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

        tableModel = new DefaultTableModel(new Object[]{"ID", "Size", "Humidity", "Temperature", "Vegetation", "Cleanliness", "Food", "Type"}, 0);
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
        addEnclosureDialog.setSize(300, 300);
        addEnclosureDialog.setLayout(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
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
                int id = Integer.parseInt(idField.getText());
                double size = Double.parseDouble(sizeField.getText());
                double humidity = Double.parseDouble(humidityField.getText());
                double temperature = Double.parseDouble(temperatureField.getText());
                double vegetationCoverage = Double.parseDouble(vegetationField.getText());
                int cleanliness = Integer.parseInt(cleanlinessField.getText());
                int foodInTrough = Integer.parseInt(foodField.getText());
                EnclosureType type = (EnclosureType) typeComboBox.getSelectedItem();

                Enclosure enclosure = new Enclosure(id, size, humidity, temperature, vegetationCoverage, cleanliness, foodInTrough, type);
                controller.addEnclosure(enclosure);
                populateTable();

                addEnclosureDialog.dispose();
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
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Enclosure enclosure = controller.getEnclosureById((int) tableModel.getValueAt(selectedRow, 0));

            JDialog editEnclosureDialog = new JDialog(this, "Edit Enclosure", true);
            editEnclosureDialog.setSize(300, 300);
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
            JLabel vegetationLabel = new JLabel("Vegetation Coverage:");
            JTextField vegetationField = new JTextField(String.valueOf(enclosure.getVegetationCoverage()));
            JLabel cleanlinessLabel = new JLabel("Cleanliness:");
            JTextField cleanlinessField = new JTextField(String.valueOf(enclosure.getZoneCleanliness()));
            JLabel foodLabel = new JLabel("Food in Trough:");
            JTextField foodField = new JTextField(String.valueOf(enclosure.getFoodInTrough()));
            JLabel typeLabel = new JLabel("Type:");
            JComboBox<EnclosureType> typeComboBox = new JComboBox<>(EnclosureType.values());
            typeComboBox.setSelectedItem(enclosure.getEnclosureType());

            JButton submitButton = new JButton("Submit");

            submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    double size = Double.parseDouble(sizeField.getText());
                    double humidity = Double.parseDouble(humidityField.getText());
                    double temperature = Double.parseDouble(temperatureField.getText());
                    double vegetationCoverage = Double.parseDouble(vegetationField.getText());
                    int cleanliness = Integer.parseInt(cleanlinessField.getText());
                    int foodInTrough = Integer.parseInt(foodField.getText());
                    EnclosureType type = (EnclosureType) typeComboBox.getSelectedItem();

                    enclosure.setSize(size);
                    enclosure.setHumidity(humidity);
                    enclosure.setTemperature(temperature);
                    enclosure.setVegetationCoverage(vegetationCoverage);
                    enclosure.setZoneCleanliness(cleanliness);
                    enclosure.setFoodInTrough(foodInTrough);
                    enclosure.setEnclosureType(type);

                    controller.updateEnclosure(enclosure);
                    populateTable();

                    editEnclosureDialog.dispose();
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
        } else {
            JOptionPane.showMessageDialog(this, "Please select an enclosure to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showDeleteEnclosureDialog() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this enclosure?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                controller.removeEnclosure(id);
                populateTable();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an enclosure to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateTable() {
        tableModel.setRowCount(0);
        controller.getEnclosureManagement().readCSV();
        for (Enclosure enclosure : controller.getEnclosureManagement().getAllEnclosures()) {
            tableModel.addRow(new Object[]{
                enclosure.getId(),
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
