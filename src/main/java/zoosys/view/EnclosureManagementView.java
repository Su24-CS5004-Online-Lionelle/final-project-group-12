package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.EnclosureType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnclosureManagementView extends JFrame{
    private controller controller;

    public EnclosureManagementView(controller controller) {
        this.controller = controller;
        setTitle("Enclosure Management");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
            public void actionPerformed(ActionEvent e){
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

        add(enclosurePanel);
        setVisible(true);
    }

    private void showAddEnclosureDialog() {
        JDialog addEnclosureDialog = new JDialog(this, "Add Enclosure", true);
        addEnclosureDialog.setSize(300, 300);
        addEnclosureDialog.setLayout(new GridLayout(7, 2));

        JLabel sizeLable = new JLabel("Size: ");
        JTextField sizeField = new JTextField();
        JLabel humidityLabel = new JLabel("Humidity: ");
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

                controller.addEnclosure(size, humidity, temperature, vegetationCoverage, cleanliness, food, type);
                addEnclosureDialog.dispose();
            }
        });
        addEnclosureDialog.add(sizeLable);
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
        JLabel typeLabel = new JLabel("Enclosure Type:");
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

                controller.addEnclosure(size, humidity, temperature, vegetationCoverage, cleanliness, food, type);
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
        deleteEnclosureDialog.setLayout(new GridLayout(2,2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                controller.removeEnclosure(id);
                deleteEnclosureDialog.dispose();
            }
        });
        
        deleteEnclosureDialog.add(idLabel);
        deleteEnclosureDialog.add(idField);
        deleteEnclosureDialog.add(deleteButton);

        deleteEnclosureDialog.setVisible(true);
    }
    
}