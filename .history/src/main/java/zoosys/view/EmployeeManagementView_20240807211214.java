package zoosys.view;

import zoosys.controller.controller;
import zoosys.model.Employee;
import zoosys.model.EmployeeManagement;
import zoosys.model.Enclosures;
import zoosys.model.Visitor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmployeeManagementView extends JFrame {
    private controller controller;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameTextField;
    private JTextArea resultTextArea;

    public EmployeeManagementView(controller controller) {
        this.controller = controller;
        setTitle("Employee Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Initialize table model with column names
        tableModel = new DefaultTableModel(new Object[]{"Name", "Role", "Shift", "Responsibilities"}, 0);

        // Create JTable with the table model
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Populate table with employee data
        populateTable();

        // Create panel for name input and results
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        nameTextField = new JTextField();
        JButton submitButton = new JButton("Submit");
        resultTextArea = new JTextArea(10, 50);
        resultTextArea.setEditable(false);

        inputPanel.add(nameTextField, BorderLayout.NORTH);
        inputPanel.add(submitButton, BorderLayout.CENTER);
        inputPanel.add(new JScrollPane(resultTextArea), BorderLayout.SOUTH);

        add(inputPanel, BorderLayout.SOUTH);

        // Add action listener to the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                removeEmployee(name);
            }
        });

        setVisible(true);
    }

    private void populateTable() {
        EmployeeManagement employeeManagement = controller.getEmployeeManagement();
        List<Employee> employees = employeeManagement.getAllEmployees();
        for (Employee employee : employees) {
            tableModel.addRow(new Object[]{
                    employee.getName(),
                    employee.getRole(),
                    employee.getShift(),
                    employee.getResponsibilities()
            });
        }
    }

    private void removeEmployee(String name) {
        controller.removeEmployee(name);
        refreshTable();
        resultTextArea.setText("Employee " + name + " removed successfully.");
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        populateTable();
    }

    public static void main(String[] args) {
        // Create the necessary model objects
        EmployeeManagement employeeManagement = new EmployeeManagement();

        // Create a Controller instance
        controller controller = new zoosys.controller.controller(employeeManagement, null, null);

        // Create and display the GUI
        new EmployeeManagementView(controller);
    }
}
